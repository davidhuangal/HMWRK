# Structure for the student JSON file

## Components

* name
* courseList
    * courseName
    * homeworkItems
        * title
        * completedStatus
        * priority

## Example:

``` json

{
  "name": "David Huangal",

  "courseList": [
    {
      "courseName": "Numerical Linear Algebra",

      "homeworkItems": [
        {
          "title": "cholesky factorization program",
          "completedStatus": "unfinished",
          "priority": "medium"
        },
        {
          "title": "take home midterm",
          "completedStatus": "finished",
          "priority": "high"
        }
      ]
    },
    {
      "courseName": "Software Engineering",

      "homeworkItems": [
        {
          "title": "Online Quiz",
          "completed status": "unfinished",
          "priority": "medium"
        },
        {
          "title": "Final Project",
          "completedStatus": "unfinished",
          "priority": "high"
        }
      ]
    }
  ]

}

```
