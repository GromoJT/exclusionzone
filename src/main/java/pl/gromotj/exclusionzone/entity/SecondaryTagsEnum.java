package pl.gromotj.exclusionzone.entity;



public enum SecondaryTagsEnum {
    APPEARANCE("APPEARANCE"),
    ADDITIONAL_INFORMATION("ADDITIONAL_INFORMATION"),
    EFFECTS("EFFECTS"),
    PERSONAL_EXPERIENCE("PERSONAL_EXPERIENCE");
    private final String text;
    SecondaryTagsEnum(String text){this.text = text;}
    public String getText(){return this.text;}

    public static SecondaryTagsEnum fromText(String text){
        for(SecondaryTagsEnum s : SecondaryTagsEnum.values()){
            if(s.getText().equals(text)){
                return s;
            }
        }
        throw new IllegalArgumentException();
    }
}
