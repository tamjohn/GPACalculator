package persistence;

import org.json.JSONObject;

/*
 * Represents a JSON interface
 * Referenced code from JsonSerializationDemo.
 * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
