package pl.gromotj.exclusionzone.entity;

public enum IndividualRecordType {
    ANOMALY("Anomaly"),
    ARTIFACT("Artifact"),
    BIOTA("Biota"),
    WILDLIFE("Wildlife");

    private final String text;

    IndividualRecordType(String text) {
        this.text = text;
    }
    public String getText(){
        return this.text;
    }

    public static IndividualRecordType fromText(String text){
        for(IndividualRecordType r : IndividualRecordType.values()){
            if(r.getText().equals(text)){
                return r;
            }
        }
        throw  new IllegalArgumentException();
    }
}
