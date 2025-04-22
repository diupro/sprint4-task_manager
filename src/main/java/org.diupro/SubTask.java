package org.diupro;

public final class SubTask extends Task{
    private final int epic_id;

    public SubTask(String name, String description, int epic_id) {
        super(name, description);
        this.epic_id = epic_id;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + this.getId() +
                ", id_epic=" + epic_id + '\'' +
                ", name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", status='" + this.getStatus() + '\'' +
                '}';
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            SubTask subTask = (SubTask) obj;
            // return Objects.equals(getId(), subTask.getId());
            return getId() == subTask.getId();
        }

        public int getEpic_id() {
            return epic_id;
        }
}
