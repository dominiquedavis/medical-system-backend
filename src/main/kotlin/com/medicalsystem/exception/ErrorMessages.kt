package com.medicalsystem.exception

// General errors
val NO_FORM_WITH_SHEET_NAME = "Form not found with sheet name: "
val NO_FORM_WITH_NAME = "Form not found with name: "
val NO_FORM_WITH_ID = "Form not found with ID: "
val FORM_IS_NULL = "Form is null"
val NO_SECTION_WITH_ID = "Section not found with ID: "
val FIELD_IS_NULL = "Field is null"
val FIELD_EXISTS_WITH_NAME = "Section already contains a formField with this name: "
val NO_FIELD_WITH_ID = "Field not found with ID: "
val USER_NOT_FOUND_WITH_ID = "User not found with ID: "
val USER_EXISTS_WITH_USERNAME = "User exists with username: "
val STATUS_UPDATED_FOR_USER = "Status updated for user "
val ADMIN_RIGHTS_UPDATED_FOR_USER = "Admin rights updated for user "
val NO_PATIENT_WITH_ID = "Patient not found with ID: "
val NO_FIELD_VALUE_FOR_FIELD_AND_PATIENT = "Field value not found for Field and Patient: "
val FIELDS_DIFFER = "Fields DTO differs from formField values"
val ERROR_UPDATING_VALUE = "An error occured when updating value: "
val NO_OPTION_WITH_VALUE = "Possible value not found for formField: "

// Import errors
val NO_DATA_ROWS_IN_SHEET = "Arkusz nie zawiera rekordów z danymi"
val NO_DATA_CELLS_IN_ROW = "Rekord nie zawiera komórek z danymi"
val PATIENT_EXISTS_WITH_ID = "Pacjent o podanym ID już istnieje: "
val PATIENT_ID_EMPTY = "ID pacjenta jest puste"
val NO_FIELD_WITH_COLUMN_INDEX = "Brak pola o indeksie kolumny: "
val INCORRECT_VALUE_IN_CELL = "Niepoprawna wartość w komórce: "