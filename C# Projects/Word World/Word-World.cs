using System;

namespace _3thAlgorithmNetwork
{
    class Program
    {
        static void Main(string[] args)
        {
            //INITIALIZING VARIABLES
            string firstInput;
            string secondInput;
            int counter;

            //RECEIVING TEXT FROM THE USER
            Console.Write("Please enter your text:");
            firstInput = Console.ReadLine();

            do
            {
                counter = 0;
                for (int i = 0; i < firstInput.Length; i++)
                {
                    //CHECKING IF THE INPUT IS VALID OR NOT
                    for (int j = 97; j <= 122; j++)
                    {
                        if (Convert.ToChar(Convert.ToString(firstInput[i]).ToLower()) == Convert.ToChar(j) || Convert.ToChar(Convert.ToString(firstInput[i]).ToLower()) == '.' || Convert.ToChar(Convert.ToString(firstInput[i]).ToLower()) == ',' || Convert.ToChar(Convert.ToString(firstInput[i]).ToLower()) == ' ')
                        {
                            counter++;
                            break;
                        }
                    }

                    //REMOVING WHITE SPACES AT THE END OF THE TEXT AND AT THE BEGINNING OF THE TEXT
                    if (firstInput[firstInput.Length - 1] == ' ')
                    {
                        firstInput = firstInput.Remove(firstInput.Length - 1, 1);
                    }
                    if (firstInput[0] == ' ')
                    {
                        firstInput = firstInput.Remove(0, 1);
                    }
                }

                //IF INPUT IS NOT VALID, RECEIVING TEXT AGAIN
                if (counter != firstInput.Length)
                {
                    Console.Write("The text can contain only English alphabet letters and two punctuations dot(.) and comma(,). So please write again: ");
                    firstInput = Console.ReadLine();
                    firstInput = firstInput.ToLower();
                }
            }
            while (counter != firstInput.Length);

            //REMOVING EXTRA WHITE SPACES. "WHITE       SPACE" TURNS INTO "WHITE SPACE" IN THIS PART.
            for (int i = 0; i < firstInput.Length - 1; i++)
            {
                if (firstInput[i] == firstInput[i + 1] && firstInput[i] == ' ')
                {
                    firstInput = firstInput.Remove(i, 1);
                    i--;
                }
            }

            //REMOVING DOTS AND COMMAS
            firstInput = firstInput.Replace(",", "");
            firstInput = firstInput.Replace(".", "");

            //SPLITTING TEXT TO ARRAYS.
            string[] originalArray = firstInput.Split(" ");
            firstInput = firstInput.ToLower();
            string[] arrayLowered = firstInput.Split(" ");

            //RECEIVING PATTERN FROM THE USER
            Console.Write("Please enter a word:");
            secondInput = Console.ReadLine();
            secondInput = secondInput.ToLower();

            if (secondInput.Length != 0)
            {
                int starCounter;
                int hyphenCounter;

                do
                {
                    //CHECKING IF THE INPUT IS VALID OR NOT
                    counter = 0;
                    starCounter = 0;
                    hyphenCounter = 0;
                    for (int i = 0; i < secondInput.Length; i++)
                    {
                        if (secondInput[i] == '*')
                        {
                            starCounter++;
                        }

                        else if (secondInput[i] == '-')
                        {
                            hyphenCounter++;
                        }

                        for (int j = 97; j <= 122; j++)
                        {
                            if (secondInput[i] == Convert.ToChar(j) || secondInput[i] == '*' || secondInput[i] == '-')
                            {
                                counter++;
                                break;
                            }
                        }
                    }

                    //IF PATTERN CONTAINS CHARACTERS THAT IS NOT INCLUDED BY ENGLISH ALPHABET, RECEIVING PATTERN AGAIN
                    if (counter != secondInput.Length)
                    {
                        Console.Write("The pattern can contain letters, * character and - character. So please write again: ");
                        secondInput = Console.ReadLine();
                        secondInput = secondInput.ToLower();
                    }

                    //IF PATTERN CONTAINS "*" CHARACTER AND "-" CHARACTER AT THE SAME TIME RECEIVING PATTERN AGAIN
                    if (hyphenCounter != 0 && starCounter != 0)
                    {
                        Console.Write("The pattern can contain either the * character and the - character but not both of them. So please write again: ");
                        secondInput = Console.ReadLine();
                        secondInput = secondInput.ToLower();
                    }
                }
                while ((counter != secondInput.Length) || (hyphenCounter != 0 && starCounter != 0));

                //REMOVING REPEATING WORDS FROM THE TEXT
                for (int i = 0; i < arrayLowered.Length; i++)
                {
                    for (int j = i; j < arrayLowered.Length; j++)
                    {
                        if ((arrayLowered[i] == arrayLowered[j]) && (i != j))
                        {
                            arrayLowered[i] = "";
                        }
                    }
                }

                //IF PATTERN EQUALS A WORD IN THE TEXT PRINTING IT.
                for (int i = 0; i < arrayLowered.Length; i++)
                {
                    if (secondInput == arrayLowered[i] && secondInput != "")
                    {
                        Console.WriteLine(originalArray[i]);
                    }
                }

                string temp = secondInput;

                for (int k = 0; k < arrayLowered.Length; k++)
                {
                    secondInput = temp;
                    for (int i = 0; i < secondInput.Length; i++)
                    {
                        //IF PATTERN CONTAINS "-" CHARACTER CHECKING IF PATTERN REPRESENTS WORDS FROM THE TEXT OR NOT
                        if (secondInput[i] == '-')
                        {

                            if (arrayLowered[k].Length == secondInput.Length)
                            {
                                for (int j = 97; j <= 122; j++)
                                {
                                    secondInput = secondInput.Remove(i, 1).Insert(i, Convert.ToString(Convert.ToChar(j)));
                                    if (secondInput[i] == arrayLowered[k][i])
                                    {
                                        break;
                                    }
                                }
                                if (secondInput == arrayLowered[k])
                                {
                                    Console.WriteLine(originalArray[k]);
                                }
                            }

                        }

                    }
                }

                secondInput += " ";
                temp = secondInput;
                for (int k = 0; k < arrayLowered.Length; k++)
                {
                    arrayLowered[k] += " ";
                    secondInput = temp;
                    for (int i = 0; i < secondInput.Length; i++)
                    {
                        //IF PATTERN CONTAINS "*" CHARACTER
                        if (secondInput[i] == '*')
                        {
                            for (int l = 0; l < secondInput.Length - 1; l++)
                            {
                                if (secondInput[l] == secondInput[l + 1] && secondInput[l] == '*')
                                {
                                    secondInput = secondInput.Remove(l, 1);
                                    l--;
                                }
                            }

                            int t = i;
                            secondInput = secondInput.Remove(i, 1);

                            if (secondInput != arrayLowered[k])
                            {
                                //IF PATTERN CONTAINS MORE THAN ONE "*" CHARACTER
                                if (secondInput.Substring(i).Contains('*'))
                                {
                                    //AVOIDING UNNECESSARY PROCESSES BY LOOKING AT THE RIGHT AND LEFT OF THE "*" CHARACTER. IF THEY DON'T MATCH WITH A WORD FROM THE TEXT IT IS UNNECESSARY TO EXECUTE THIS PART.
                                    if (arrayLowered[k].Contains(secondInput.Substring(0, i)) && arrayLowered[k].Contains(secondInput.Substring(i, secondInput.IndexOf('*', i) - i)))
                                    {
                                        while ((secondInput.Substring(0, secondInput.IndexOf('*', i)) != arrayLowered[k].Substring(0, secondInput.IndexOf('*', i)))
                                    && (secondInput.Substring(0, secondInput.IndexOf(' ')) != arrayLowered[k].Substring(0, arrayLowered[k].IndexOf(' '))))
                                        {
                                            for (int j = 97; j <= 122; j++)
                                            {
                                                secondInput = secondInput.Insert(t, Convert.ToString(Convert.ToChar(j)));
                                                if (secondInput[t] == arrayLowered[k][t])
                                                {
                                                    break;
                                                }
                                                else
                                                {
                                                    secondInput = secondInput.Remove(t, 1);
                                                }
                                            }
                                            t++;
                                        }
                                    }
                                    else
                                        break;
                                }

                                //IF PATTERN CONTAINS ONE "*" CHARACTER
                                else
                                {
                                    //AVOIDING UNNECESSARY PROCESSES BY LOOKING AT THE RIGHT AND LEFT OF THE "*" CHARACTER. IF THEY DON'T MATCH WITH A WORD FROM THE TEXT IT IS UNNECESSARY TO EXECUTE THIS PART.
                                    if (((arrayLowered[k].Length - 1 - (secondInput.IndexOf(' ') - i) >= 0) && (secondInput.Substring(i, secondInput.IndexOf(' ') - i) == arrayLowered[k].Substring(arrayLowered[k].Length - 1 - (secondInput.IndexOf(' ') - i), secondInput.IndexOf(' ') - i)))
                                        && ((arrayLowered[k].Length >= i) && (arrayLowered[k].Substring(0, i) == secondInput.Substring(0, i))))
                                    {
                                        while (secondInput.Substring(0, secondInput.IndexOf(' ')) != arrayLowered[k].Substring(0, arrayLowered[k].IndexOf(' ')))
                                        {
                                            for (int j = 97; j <= 122; j++)
                                            {
                                                secondInput = secondInput.Insert(t, Convert.ToString(Convert.ToChar(j)));
                                                if (secondInput[t] == arrayLowered[k][t])
                                                {
                                                    break;
                                                }
                                                else
                                                {
                                                    secondInput = secondInput.Remove(t, 1);
                                                }
                                            }
                                            t++;
                                        }
                                    }
                                    else
                                        break;
                                }

                                //CHECKING IF PATTERN REPRESENTS WORDS FROM THE TEXT OR NOT. IF IT DOES PRINTING WORD.
                                if (arrayLowered[k] == secondInput)
                                {
                                    Console.WriteLine(originalArray[k]);
                                }
                            }
                            else
                            {
                                //CHECKING IF PATTERN REPRESENTS WORDS FROM THE TEXT OR NOT. IF IT DOES PRINTING WORD.
                                if (arrayLowered[k] == secondInput)
                                {
                                    Console.WriteLine(originalArray[k]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
