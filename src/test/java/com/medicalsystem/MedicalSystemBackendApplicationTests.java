package com.medicalsystem;

import com.medicalsystem.model.Section;
import com.medicalsystem.service.SectionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalSystemBackendApplicationTests {

    @Autowired
    private SectionService sectionService;

	@Test
	public void generalTests() {
		Section section = sectionService.findById(1);

        Assert.assertNotNull(section);
        Assert.assertTrue(section.getFields().size() == 3);
	}

}
