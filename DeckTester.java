
class DeckTester{
    public static void main(String[] args){
        Deck AceofSpades = new Deck();

        AceofSpades.shuffle();
        System.out.println(AceofSpades + "\n");

        for (int i = 0; i < 53; i++)
        System.out.println(AceofSpades.deal());

        
        
}
}