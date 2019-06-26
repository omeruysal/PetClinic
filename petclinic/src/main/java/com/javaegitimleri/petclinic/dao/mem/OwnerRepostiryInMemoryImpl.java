package com.javaegitimleri.petclinic.dao.mem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.model.Owner;

@Repository //repository bean yaratırız
public class OwnerRepostiryInMemoryImpl implements OwnerRepository {

	private Map<Long, Owner> ownersMap = new HashMap<Long, Owner>(); //ornek ownerları tutacagımız map 

	public OwnerRepostiryInMemoryImpl() {
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("omer");
		owner1.setLastName("uysal");
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("owner2");
		owner2.setLastName("uysal");
		Owner owner3 = new Owner();
		owner3.setId(3L);
		owner3.setFirstName("owner3");
		owner3.setLastName("meli");
		ownersMap.put(owner1.getId(), owner1);
		ownersMap.put(owner2.getId(), owner2);
		ownersMap.put(owner3.getId(), owner3);
	}

	@Override
	public List<Owner> findAll() {
		return new ArrayList<Owner>(ownersMap.values());
	}

	@Override
	public Owner findById(Long id) {
		return ownersMap.get(id);
	}

	@Override
	public List<Owner> findByLastName(String lastName) {
				
		
		return ownersMap.values().stream().filter(o->o.getLastName().equals(lastName)).collect(Collectors.toList());
		}

	@Override
	public void create(Owner owner) {
		owner.setId(new Date(0).getTime());
		ownersMap.put(owner.getId(), owner);
	}

	@Override
	public Owner update(Owner owner) {
		ownersMap.replace(owner.getId(), owner);
		return owner;
	}

	@Override
	public void delete(Long id) {
		ownersMap.remove(id);
	}

}
