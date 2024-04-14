using System;

namespace _2ndAlgorithmHomeworkSideBySide
{
    class Program
    {
        static void Main(string[] args)
        {
            //Initializing Arrays
            string[] array1 = new string[15];
            string[] array2 = new string[15];
            string[] array3 = new string[15];
            string[] array4 = new string[3] { "D", "E", "U" };
            string[] names = new string[12] { "Derya     ", "Elife     ", "Fatih     ", "Ali       ", "Azra      ", "Sibel     ", "Cem       ", "Nazan     ", "Mehmet    ", "Nil       ", "Can       ", "Tarkan    " };
            int[] scores = new int[12] { 100, 100, 95, 90, 85, 80, 80, 70, 55, 50, 30, 30 };
            string[] namesNew = new string[13];
            int[] scoresNew = new int[13];

            //Filling Arrays With Space for Future Usage
            for (int i = 0; i < array1.Length; i++)
            {
                array1[i] = " ";
            }

            for (int i = 0; i < array2.Length; i++)
            {
                array2[i] = " ";
            }

            for (int i = 0; i < array3.Length; i++)
            {
                array3[i] = " ";
            }

            //Initializing Variables
            Random random1 = new Random();
            Random random2 = new Random();
            int randomInt1;
            int randomInt2;
            ConsoleKeyInfo key1;
            int whoseturn = 0;
            int player1 = 120;
            int player2 = 120;
            int controlSpace = 0;
            int countMoves = 0;
            int controlBreak = 0;
            string winner = "";

            //Welcome Sign
            Console.WriteLine("Welcome! To start game and be the first player please press any button. \n");
            do
            {
                whoseturn++;
                countMoves++;
                key1 = Console.ReadKey(true);
                //Checking Whose Turn
                if (whoseturn % 2 == 1)
                {
                    player1 = player1 - 5;
                    Console.WriteLine(countMoves + "Player1                      P1 = " + player1 + " P2 = " + player2);
                }
                else
                {
                    player2 = player2 - 5;
                    Console.WriteLine(countMoves + "Player2                      P1 = " + player1 + " P2 = " + player2);
                }

                //Random Array Choosing and Controling If They are Empty
                do
                {
                    randomInt1 = random1.Next(1, 4);
                    if (randomInt1 == 1)
                    {
                        for (int i = 0; i < array1.Length; i++)
                        {
                            if (array1[i] == " ")
                            {
                                controlSpace++;
                            }

                        }
                    }
                    else if (randomInt1 == 2)
                    {
                        for (int i = 0; i < array2.Length; i++)
                        {
                            if (array2[i] == " ")
                            {
                                controlSpace++;
                            }

                        }
                    }
                    else if (randomInt1 == 3)
                    {
                        for (int i = 0; i < array3.Length; i++)
                        {
                            if (array3[i] == " ")
                            {
                                controlSpace++;
                            }

                        }
                    }


                }
                while (controlSpace == 0);
                controlSpace = 0;
                //Randomly Choosing Which Letter is Going to be Placed
                switch (randomInt1)
                {
                    case 1:
                        randomInt2 = random2.Next(3);
                        for (int i = 0; i < array1.Length; i++)
                        {
                            if (array1[i] == " ")
                            {
                                array1[i] = array4[randomInt2];
                                break;
                            }
                        }
                        break;
                    case 2:
                        randomInt2 = random2.Next(3);
                        for (int i = 0; i < array2.Length; i++)
                        {
                            if (array2[i] == " ")
                            {
                                array2[i] = array4[randomInt2];
                                break;
                            }
                        }
                        break;
                    case 3:
                        randomInt2 = random2.Next(3);
                        for (int i = 0; i < array3.Length; i++)
                        {
                            if (array3[i] == " ")
                            {
                                array3[i] = array4[randomInt2];
                                break;
                            }
                        }
                        break;

                }
                //Writing Arrays With New Letter
                for (int i = 0; i < array1.Length; i++)
                {
                    Console.Write(array1[i]);
                }
                Console.WriteLine();
                for (int i = 0; i < array2.Length; i++)
                {
                    Console.Write(array2[i]);
                }
                Console.WriteLine();
                for (int i = 0; i < array3.Length; i++)
                {
                    Console.Write(array3[i]);
                }
                Console.WriteLine('\n');

                //Checking Matches
                for (int i = 1; i < (array2.Length - 1); i++)
                {
                    if (array2[i] == "E" && ((array1[i - 1] == "D" && array3[i + 1] == "U") || (array1[i + 1] == "D" && array3[i - 1] == "U") || (array1[i - 1] == "U" && array3[i + 1] == "D") || (array1[i + 1] == "U" && array3[i - 1] == "D")))
                    {
                        if (whoseturn % 2 == 1)
                        {
                            Console.WriteLine("Winner: Player1 \n");
                            controlBreak++;
                            winner = "player1";
                        }
                        else
                        {
                            Console.WriteLine("Winner: Player2 \n");
                            controlBreak++;
                            winner = "player2";
                        }
                    }
                }
                //Checking Matches
                for (int i = 0; i < array2.Length; i++)
                {
                    if (array2[i] == "E" && ((array1[i] == "D" && array3[i] == "U") || (array1[i] == "U" && array3[i] == "D")))
                    {
                        if (whoseturn % 2 == 1)
                        {
                            Console.WriteLine("Winner: Player1 \n");
                            controlBreak++;
                            winner = "player1";
                        }
                        else
                        {
                            Console.WriteLine("Winner: Player2 \n");
                            controlBreak++;
                            winner = "player2";
                        }
                    }
                }
                //Still Checking Matches
                for (int i = 1; i < (array2.Length - 1); i++)
                {
                    if ((array1[i] == "E" && array1[i - 1] == "D" && array1[i + 1] == "U") || (array1[i] == "E" && array1[i - 1] == "U" && array1[i + 1] == "D")
                        || (array2[i] == "E" && array2[i - 1] == "D" && array2[i + 1] == "U") || (array2[i] == "E" && array2[i - 1] == "U" && array2[i + 1] == "D")
                        || (array3[i] == "E" && array3[i - 1] == "D" && array3[i + 1] == "U") || (array3[i] == "E" && array3[i - 1] == "U" && array3[i + 1] == "D"))
                    {
                        if (whoseturn % 2 == 1)
                        {
                            Console.WriteLine("Winner: Player1 \n");
                            controlBreak++;
                            winner = "player1";
                        }
                        else
                        {
                            Console.WriteLine("Winner: Player2 \n");
                            controlBreak++;
                            winner = "player2";
                        }
                    }
                }
                if (controlBreak == 1)
                {
                    break;
                }
            }
            while (countMoves != 45);

            //Placing Player to the Score Table and Writing Score Table or When the Game is Tie Just Writing Score Table
            if (controlBreak == 1)
            {
                if (winner == "player1")
                {
                    for (int i = scores.Length - 1; i >= 0; i--)
                    {
                        if (scores[i] >= player1)
                        {
                            for (int j = i; j >= 0; j--)
                            {
                                scoresNew[j] = scores[j];
                                namesNew[j] = names[j];
                            }
                            scoresNew[i + 1] = player1;
                            namesNew[i + 1] = "Player1   ";
                            break;
                        }
                        else
                        {
                            scoresNew[i + 1] = scores[i];
                            namesNew[i + 1] = names[i];
                        }
                    }
                }
                else if (winner == "player2")
                {
                    for (int i = scores.Length - 1; i >= 0; i--)
                    {
                        if (scores[i] >= player2)
                        {
                            for (int j = i; j >= 0; j--)
                            {
                                scoresNew[j] = scores[j];
                                namesNew[j] = names[j];
                            }
                            scoresNew[i + 1] = player2;
                            namesNew[i + 1] = "Player2   ";
                            break;
                        }
                        else
                        {
                            scoresNew[i + 1] = scores[i];
                            namesNew[i + 1] = names[i];
                        }
                    }
                }
                Console.WriteLine("NAME      SCORE");

                for (int i = 0; i < namesNew.Length; i++)
                {
                    Console.WriteLine(namesNew[i] + scoresNew[i]);
                }
            }

            else
            {
                Console.WriteLine("The Game is Draw. \n");
                Console.WriteLine("NAME      SCORE");

                for (int i = 0; i < names.Length; i++)
                {
                    Console.WriteLine(names[i] + scores[i]);
                }
            }
        }
    }
}
