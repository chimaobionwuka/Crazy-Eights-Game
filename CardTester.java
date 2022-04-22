
class CardTester
{
    public static void main(String[] args)
    {

        char suit = args[0].charAt(0);
        int rank = Integer.parseInt(args[0].substring(1,args[0].length()));
 
        Card c = new Card(suit, rank);
        System.out.println(c);
    }
}