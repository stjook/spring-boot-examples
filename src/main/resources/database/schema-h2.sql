------------------------------------------------------------------------
--
-- tEmployee
--
-- It stores Employee's data.
--
--
-- employeeId         An auto increment id for the employee.
--
-- social_security    The social security Id of the employee.
--
-- first_name         The firstname of the employee.
--
-- last_name          The lastname of the employee.
--
-- salary             The salary of the employee.
--
------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tEmployee (
    employee_id integer auto_increment PRIMARY KEY,
    social_security integer NOT NULL,
    first_name varchar(32) NOT NULL,
    last_name varchar(32) NOT NULL,
    salary double NOT NULL,
    CONSTRAINT ctEmployee_idx1 UNIQUE (social_security)
);
