package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Project {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private String announcement;
    @Expose
    private boolean show_announcement;
    @Expose
    private int suite_mode;
}
