package lab9.ServerConnector;


public class Levenshtein {
    public static int levenshtein(String first, String second) {
        int i, j, f_len, s_len, cost;
        int d[][];
        f_len = first.length();
        s_len = second.length();

        d = new int[f_len+1][s_len+1];

        for (i=0; i<=f_len; i++)
            d[i][0] = i;
        for (j=1; j<=s_len; j++)
            d[0][j] = j;

        for (i=1; i<=f_len; i++)
        {
            for (j=1; j<=s_len; j++)
            {
                if (first.charAt(i-1) == second.charAt(j-1))
                    cost = 0;
                else
                    cost = 1;

                d[i][j] = Math.min(d[i-1][j] + 1,         /* remove */
                        Math.min(d[i][j-1] + 1,      /* insert */
                                d[i-1][j-1] + cost));   /* change */
            }
        }
        return d[f_len][s_len];
    }

    
    
}