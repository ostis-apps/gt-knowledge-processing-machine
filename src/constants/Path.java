package constants;


public enum Path {
    OPEN_ICON("src/images/open.png"),
    SAVE_ICON("src/images/save.png"),
    NEW_FILE_ICON("src/images/new.png"),
    
    SEARCH_ICON("src/images/search.png"),
    ADD_ICON("src/images/add.png"),
    DELETE_ICON("src/images/delete.png"),
    
    LEFT_ICON("src/images/left1.png"),
    LEFT_START_ICON("src/images/left_start.png"),
    RIGHT_ICON("src/images/right1.png"),
    RIGHT_END_ICON("src/images/right_end.png");
    
    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}


