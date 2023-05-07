package PetStoreAutomation;

public class PetId {
    private long petId;

    public PetId(long petId) {
        this.petId = petId;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    @Override
    public String toString() {
        return "PetId{" +
                "petId=" + petId +
                '}';
    }
}
