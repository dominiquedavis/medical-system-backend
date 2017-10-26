package com.medicalsystem.service.impl;

import com.medicalsystem.model.Section;
import com.medicalsystem.repository.SectionRepository;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Override
    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getById(Long id) {
        return sectionRepository.findOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return sectionRepository.exists(id);
    }

    @Override
    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void deleteById(Long id) {
        sectionRepository.delete(id);
    }
}
