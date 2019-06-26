package com.javaegitimleri.petclinic.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javaegitimleri.petclinic.exception.OwnerNotFoundException;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.service.PetClinicService;

@RestController // handler fonkisyonlarına ayrı ayrı responsebody yazmamıza gerek kalmaz ve
				// controller beanı olusur
@RequestMapping("/rest") // butun handler methodların requestmappinglerine teker teker buradakı prefixi
							// eklemek zorunda kalmayız
public class PetClinicRestController {

	@Autowired // petclinicservice tipindeki beanimizi controller beanine enjekte edecek
	private PetClinicService petClinicService;

	@RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
	public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody Owner ownerRequest) {
		try {
			Owner owner = petClinicService.findOwner(id);
			owner.setFirstName(ownerRequest.getFirstName());
			owner.setLastName(ownerRequest.getLastName());
			petClinicService.updateOwner(owner);
			return ResponseEntity.ok().build();

		} catch (OwnerNotFoundException ex) {
			return ResponseEntity.notFound().build();
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		

	}

	@RequestMapping(method = RequestMethod.POST, value = "/owner")
	public ResponseEntity<URI> createOwner(@RequestBody Owner owner) // yaratma islemi sonrası owner kaydına erısımı
																		// saglayacak URI donecegımız ıcın
	{
		try {

			petClinicService.createOwner(owner);
			Long id = owner.getId(); // burada id alıp currentrequestin uri ile birlestirip yeni bir uri olustururz
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();// owners nesnesıne ulasıcakrest get uri ni olusturup onu
															// dondurur
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/owners")
	public ResponseEntity<List<Owner>> getOwners() {// ResponseEntity ile return edilicek
													// icerik hemde statik kodu birlikte ifade edilir
		List<Owner> owners = petClinicService.findOwners();
		return ResponseEntity.ok(owners);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/owner")
	public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastName)// web istegi geldiginde ?ln nin
	// degeri buradaki lastName e aktarılacak
	{
		List<Owner> owners = petClinicService.findOwners(lastName);
		return ResponseEntity.ok(owners);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")

	public ResponseEntity<Owner> getOwner(@PathVariable("id") long id)// yukarıdaki id yi pathten alır
	{
		try {
			Owner owner = petClinicService.findOwner(id);
			return ResponseEntity.ok(owner);
		} catch (Exception e) {

			return ResponseEntity.notFound().build();
		}

	}

}
