package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Milestone {
    @Expose
    int id;
    @Expose
    String name;
    @Expose
    String description;
    @Expose
    String refs;
    @Expose
    long start_on;
    @Expose
    long started_on;
    @Expose
    long due_on;
    @Expose
    long completed_on;
    @Expose
    boolean is_started;
    @Expose
    boolean is_completed;
}
