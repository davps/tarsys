package com.logicalnode.tarsys.web;

public class DescriptionUniquenessViolationException extends RuntimeException {
    private String description;

    public DescriptionUniquenessViolationException(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
