//public class SQLInsertsTest {
//    @Test
//    public void testSqlInsert() {
//        MockNeat m = MockNeat.threadLocal();
//
//        // Manager ids
//        List<Integer> managerIds = m.ints()
//                                    .range(1, 1000)
//                                    .list(() -> new ArrayList<>(), 20)
//                                    .val();
//
//        // ----- Regions -----
//        String[] regionNames = new String[] {"Europe", "Americas", "Asia", "Middle East and Africa" };
//        SQLTable regions = m.sqlInserts()
//                            .tableName("regions")
//                            .column("region_id", m.intSeq().start(1))
//                            .column("region_name", m.seq(regionNames), TEXT_BACKSLASH)
//                            .table(regionNames.length)
//                            .val();
//        System.out.println(regions);
//
//        // ----- Countries -----
//        int numCountries = 241;
//        SQLTable countries = m.sqlInserts()
//                              .tableName("countries")
//                              .column("country_id", m.seq(COUNTRY_ISO_CODE_2), TEXT_BACKSLASH)
//                              .column("country_name", m.seq(COUNTRY_NAME), TEXT_BACKSLASH)
//                              .column("region_id", regions.fromColumn("region_id"))
//                              .table(numCountries)
//                              .val();
//        System.out.println(countries);
//
//        // ----- Locations -----
//        int numLocations = 100;
//
//        MockUnitString streetAddressGen = m.fmt("#{num} #{noun} #{end}")
//                                           .param("num", m.ints().range(10, 2000))
//                                           .param("noun", m.words().nouns().format(CAPITALIZED))
//                                           .param("end", m.from(new String[]{"Ave", "St", "Blvd", "Rd"}));
//
//        MockUnitString postalCodeGen = m.fmt("#{word1} #{word2}")
//                                        .param("word1", m.strings().size(3).format(UPPER_CASE))
//                                        .param("word2", m.strings().size(3).format(UPPER_CASE));
//
//        SQLTable locations = m.sqlInserts()
//                              .tableName("locations")
//                              .column("location_id",    m.intSeq().start(1000).increment(100))
//                              .column("street_address", streetAddressGen, TEXT_BACKSLASH)
//                              .column("postal_code",    postalCodeGen, TEXT_BACKSLASH)
//                              .column("city",           m.cities().us(), TEXT_BACKSLASH)
//                              .column("state_province", m.cities().capitals(), TEXT_BACKSLASH)
//                              .column("country_id",     countries.fromColumn("country_id"), TEXT_BACKSLASH)
//                              .table(numLocations)
//                              .val();
//
//        System.out.println(locations);
//
//        // ----- Departments -----
//        int depNum = 10;
//
//        SQLTable departments = m.sqlInserts()
//                                .tableName("departments")
//                                .column("department_id", m.intSeq().start(0).increment(10))
//                                .column("department_name", m.seq(DEPARTMENTS), TEXT_BACKSLASH)
//                                .column("manager_id", m.from(managerIds))
//                                .column("location_id", locations.fromColumn("location_id"))
//                                .table(depNum)
//                                .val();
//
//        System.out.println(departments);
//
//        // ----- JOBS -----
//        String[] jobNames = new String[] {"Analyst",
//                                          "Consultant",
//                                          "Senior Consultant",
//                                          "Manager",
//                                          "Software Architect",
//                                          "Senior Manager",
//                                          "Director"};
//
//        String[] jobIds = new String[] { "A", "C", "SC", "M", "SA", "SM", "D" };
//
//        int numJobs = jobNames.length;
//        int minSalary = 2000;
//        int maxSalary = 5000;
//
//
//        SQLTable jobs = m.sqlInserts()
//                         .tableName("jobs")
//                         .column("job_id", m.seq(jobIds), TEXT_BACKSLASH)
//                         .column("job_title", m.seq(jobNames), TEXT_BACKSLASH)
//                         .column("min_salary", minSalary + "")
//                         .column("max_salary", maxSalary + "")
//                         .table(numJobs)
//                         .val();
//
//        System.out.println(jobs);
//
//        //  ----- EMPLOYEES -----
//
//        int numEmployes = 1000;
//
//        SQLTable employees = m.sqlInserts()
//                              .tableName("employees")
//                              .column("employee_id", m.intSeq())
//                              .column("first_name", m.names().first(), TEXT_BACKSLASH)
//                              .column("last_name", m.names().last(), TEXT_BACKSLASH)
//                              .column("email", m.emails().domain("corp.com"), TEXT_BACKSLASH)
//                              .column("phone_number", m.regex("\\+30 [0-9]{9}"), TEXT_BACKSLASH)
//                              .column("hire_date", m.localDates()
//                                                            .past(LocalDate.of(2000, 1, 1))
//                                                            .display(BASIC_ISO_DATE), TEXT_BACKSLASH)
//                              .column("job_id", jobs.fromColumn("job_id"), TEXT_BACKSLASH)
//                              .column("salary", m.ints().range(minSalary, maxSalary))
//                              .column("commission_pct", "NULL")
//                              .column("manager_id", "NULL" /* TO UPDATE LATER */)
//                              .column("department_id", departments.fromColumn("department_id"))
//                              .table(numEmployes)
//                              .val();
//
//        // Updating employees with their manager ID
//        // - One person has no manager ID - it should be a director level person
//        // - Manager id and employee_id should be different
//
//        // We update all people with a manager_id
//        employees.updateAll((i, insert) -> {
//            Integer employeeId = parseInt(insert.getValue("employee_id"));
//            Integer managerId = Integer.MIN_VALUE;
//
//            while(employeeId == (managerId = m.from(managerIds).val()));
//
//            insert.setValue("manager_id", managerId + "");
//        });
//
//        // One of the directors doesn't have a manager id as he is the CO
//        employees.selectFirstWhere(sqlInsert -> sqlInsert.getValue("job_id").equals("D"))
//                    .get()
//                    .setValue("manager_id", "NULL");
//
//        System.out.println(employees);
//    }
//}
