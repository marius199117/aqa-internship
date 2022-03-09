Feature: As A User
  I Want To Be Able To Manipulate Data In Project Table

  Scenario: Insert New Data
    When I "insert" Data In Table "project_table" With Specific Data "project"
    Then I Verify That "project" Data Is "added" In "project_table"

  Scenario: Update Data
    When I "update" Data In Table "project_table" With Specific Data "project_up"
    Then I Verify That "project_up" Data Is "updated" In "project_table"

  Scenario: Delete Data
                                                                #id
    When I "dte" Data In Table "project_table" With Specific Data "project"
    Then I Verify That ele"project" Data Is "deleted" In "project_table"
                                 #id              #from


