package com.javaegitimleri.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.exception.OwnerNotFoundException;
import com.javaegitimleri.petclinic.model.Owner;
@Service
public class PetClinicServiceImpl implements PetClinicService{

	private OwnerRepository ownerRepository;

		@Autowired // OwnerRepository tipinde ki bir bean PetclinicService beane içerisine enjekte eder
		public void setOwnerRepository(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}
	
	
	@Override
	public List<Owner> findOwners() {
		return ownerRepository.findAll();
	}

	@Override
	public List<Owner> findOwners(String lastName) {
		
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public Owner findOwner(Long id) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findById(id);
		if(owner==	null) throw new OwnerNotFoundException(id+" id numaralı owner bulunamadı");
		return owner;
	}

	@Override
	public void createOwner(Owner owner) {
		ownerRepository.create(owner);
		
	}

	@Override
	public void deleteOwner(Long id) {
		ownerRepository.delete(id);
		
	}

	@Override
	public void updateOwner(Owner owner) {
		ownerRepository.update(owner);
		
	}

}
