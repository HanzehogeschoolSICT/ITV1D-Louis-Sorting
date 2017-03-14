package models;

public class StepStateModel {
    private boolean nextStep = false;

    /**
     * Check if the algorithm is waiting for the next step.
     *
     * @return True if the algorithm is waiting for the next step, false otherwise.
     */
    public boolean isNextStep() {
        return nextStep;
    }

    /**
     * Set if the algorithm is waiting for the next step.
     *
     * @param nextStep True if the algorithm is waiting for the next step, false otherwise.
     */
    public void setNextStep(boolean nextStep) {
        this.nextStep = nextStep;
    }
}
