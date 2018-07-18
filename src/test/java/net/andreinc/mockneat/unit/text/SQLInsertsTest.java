package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.unit.text.sql.SQLTable;
import net.andreinc.mockneat.unit.text.sql.SQLEscaper;
import net.andreinc.mockneat.utils.SqlEscapeUtils;
import org.junit.Test;
import static net.andreinc.mockneat.Constants.M;

public class SQLInsertsTest {
    @Test
    public void testSqlInsert() {
        SQLTable users = M.sqlInserts()
                .tableName("USERS")
                .column("id", M.intSeq())
                .column("first_name", M.names().first(), SqlEscapeUtils.MySQL.TEXT)
                .column("last_name", M.names().last(), SqlEscapeUtils.MySQL.TEXT)
                .table(5)
                .val();

        String[] rolesName = new String[] { "Analyst", "Cons'ultant", "Senior Consultant", "Manager", "Senior Manager" };

        SQLTable roles = M.sqlInserts()
                          .column("role_id", M.intSeq())
                          .column("role_name", M.from(rolesName), SqlEscapeUtils.MySQL.TEXT)
                          .table(rolesName.length)
                          .val();

//
//        System.out.println(users);
//        System.out.println(roles);

        System.out.println(SQLEscaper.MySQLText.escape("Andrei'\\oo"));

    }
}
