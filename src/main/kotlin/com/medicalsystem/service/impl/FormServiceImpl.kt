package com.medicalsystem.service.impl

import com.medicalsystem.factory.FormDTOFactory
import com.medicalsystem.model.Form
import com.medicalsystem.model.Patient
import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.model.dto.SectionDTO
import com.medicalsystem.repository.FormRepository
import com.medicalsystem.service.FormService
import com.medicalsystem.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FormServiceImpl @Autowired constructor(
        val formRepository: FormRepository,
        val formDTOFactory: FormDTOFactory,
        val patientService: PatientService) : FormService(formRepository) {

    override fun getBySheetIndex(sheetIndex: Int): Form? =
            formRepository.findBySheetIndex(sheetIndex)

    override fun getAllAsDTO(): List<FormDTO> =
            formDTOFactory.toDTO(getAll(), null)

    override fun getFormDTOForPatient(patientId: String): FormDTO? {
        val patient: Patient? = patientService.getById(patientId)
        patient?.form?.let {
            return formDTOFactory.toDTO(it, patient)
        }
        return null
    }

    override fun addSection(sectionDTO: SectionDTO, formId: Long) {
        TODO()
    }
}