# Personal Project: GPA Calculator

## Percentage to GPA Application 

This application will convert courses graded in a percentage to either a 4.0 or 4.33 GPA scale.
<br>

GPA is a very common measure of grades. Many schools and sometimes even employers may require a certain GPA score 
just to submit an application. However, UBC grades on a percentage scale
and thus, does not offer scores based on the GPA metric.
<br>

With this application, users will be able to take the grades they earned 
in a percentage-based course and convert it into the GPA scale to submit
to other post-secondary schools, employers, or simply list on their resume.
<br>

## User Stories:<br>
- As a user, I want to be able to add multiple courses with their associated percent grades <br>
- As a user, I want to be able to view a list of all the courses I entered with its corresponding percent grade <br>
- As a user, I want to be able to delete any of the courses I entered <br>
- As a user, I want to be able to convert my list of courses into a 4.0 or 4.33 GPA scale <br>
- As a user, I want to be able to save the courses and percent grades I entered to file <br>
- As a user, I want to be able to load my previously entered courses and percent
grades from file <br>

## Phase 4: Task 2<br>
- Examples of event logs printed in console when courses are added, deleted or converted to GPA scales:
   - Thu Mar 31 23:19:47 PDT 2022 <br>
     Added cpsc110 to list of courses. <br>
   - Thu Mar 31 23:19:47 PDT 2022 <br> 
   Removed cpsc110 from list of courses. <br>
   - Thu Mar 31 23:19:47 PDT 2022 <br>
     Converted list of courses to 4.0 GPA. <br>
   - Thu Mar 31 23:19:47 PDT 2022 <br>
     Converted list of courses to 4.33 GPA.

## Phase 4: Task 3<br>
  - Reflection on the project design: <br>
    - Currently, all the code and functionality of my GUI is on one single page. If I had more time,
    I would have refactored the GUI to separate the functionality of responsibilities and also create 
    separate classes to handle buttons, images etc