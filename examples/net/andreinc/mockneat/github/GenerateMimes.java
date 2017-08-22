package andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 21/08/17.
 */
public class GenerateMimes {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        String mime = m.mimes().val();
        System.out.println(mime);
    }
}
