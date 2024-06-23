package dev.elshan.exception;

public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String field;
    private String fieldName;
    private Long fieldId;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found with %s: %s", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String resourceName, Long fieldId) {
        super(String.format("%s not found with id %d",resourceName,fieldId));
        this.resourceName = resourceName;
        this.fieldId = fieldId;
    }
}
