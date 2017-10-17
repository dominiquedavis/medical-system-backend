package com.medicalsystem.service.impl;

import com.medicalsystem.model.Section;
import com.medicalsystem.repository.SectionRepository;
import com.medicalsystem.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Override
    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Section findById(Integer id) {
        return sectionRepository.findOne(id);
    }

    @Override
    public Section saveOrUpdate(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void delete(Section section) {
        sectionRepository.delete(section);
    }

    @Override
    public void deleteById(Integer id) {
        sectionRepository.delete(id);
    }

    @Override
    public Section findByName(String name) {
        return sectionRepository.findByName(name);
    }
}
