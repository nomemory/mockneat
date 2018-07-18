package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.unit.text.sql.SQLTable;
import org.junit.Test;
import static net.andreinc.mockneat.Constants.M;

public class SQLInsertsTest {
    @Test
    public void testSqlInsert() {

        SQLTable users = M.sqlInserts()
                .tableName("USERS")
                .column("id", M.intSeq())
                .column("first_name", M.names().first())
                .column("last_name", M.names().last())
                .table(5)
                .val();

        String[] rolesName = new String[] { "Analyst", "Cons'ultant", "Senior Consultant", "Manager", "Senior Manager" };

        SQLTable roles = M.sqlInserts()
                          .column("role_id", M.intSeq())
                          .column("role_name", M.from(rolesName))
                          .table(rolesName.length)
                          .val();


    }
}
