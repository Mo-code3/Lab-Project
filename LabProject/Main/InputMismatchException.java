package LabProject.Main;

// This file contains package-private exception classes
// Public versions are in separate files for proper visibility

class InputMismatchException extends Exception {
    public InputMismatchException(String message) {
        super(message);
    }
}