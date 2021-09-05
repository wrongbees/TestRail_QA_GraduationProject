package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Project {
    private int id;
    private String name;
    private String announcement;
    private boolean show_announcement;
    private int suite_mode;
}
