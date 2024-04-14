def Word_Order_Frequency_One_Book(book, wordOrder, result):
    array=[""]
    result=""
    dictionary1={}
    control1=-1
    editing1=13
    
    if (wordOrder == 1):      
        a = open(book,'r', encoding="utf8")
        bStr = a.read()
        a.close()
        bStr = bStr.lower()
        #Replacing new line character with space character.
        bStr = bStr.replace("\n"," ")
        stopWordsStr = "able about above abroad according accordingly across actually adj after afterwards again against ago ahead ain't all allow allows almost alone along alongside already also although always am amid amidst among amongst an and another any anybody anyhow anyone anything anyway anyways anywhere apart appear appreciate appropriate are aren't around as a's aside ask asking associated at available away awfully back backward backwards be became because become becomes becoming been before beforehand begin behind being believe below beside besides best better between beyond both brief but by came can cannot cant can't caption cause causes certain certainly changes clearly c'mon co co. com come comes concerning consequently consider considering contain containing contains corresponding could couldn't course c's currently dare daren't definitely described despite did didn't different directly do does doesn't doing done don't down downwards during each edu eg eight eighty either else elsewhere end ending enough entirely especially et etc even ever evermore every everybody everyone everything everywhere ex exactly example except fairly far farther few fewer fifth first five followed following follows for forever former formerly forth forward found four from further furthermore get gets getting given gives go goes going gone got gotten greetings had hadn't half happens hardly has hasn't have haven't having he he'd he'll hello help hence her here hereafter hereby herein here's hereupon hers herself he's hi him himself his hither hopefully how howbeit however hundred i'd ie if ignored i'll i'm immediate in inasmuch inc inc. indeed indicate indicated indicates inner inside insofar instead into inward is isn't it it'd it'll its it's itself i've just k keep keeps kept know known knows last lately later latter latterly least less lest let let's like liked likely likewise little look looking looks low lower ltd made mainly make makes many may maybe mayn't me mean meantime meanwhile merely might mightn't mine minus miss more moreover most mostly mr mrs much must mustn't my myself name namely nd near nearly necessary need needn't needs neither never neverf neverless nevertheless new next nine ninety no nobody non none nonetheless noone no-one nor normally not nothing notwithstanding novel now nowhere obviously of off often oh ok okay old on once one ones one's only onto opposite or other others otherwise ought oughtn't our ours ourselves out outside over overall own particular particularly past per perhaps placed please plus possible presumably probably provided provides que quite qv rather rd re really reasonably recent recently regarding regardless regards relatively respectively right round said same saw say saying says second secondly see seeing seem seemed seeming seems seen self selves sensible sent serious seriously seven several shall shan't she she'd she'll she's should shouldn't since six so some somebody someday somehow someone something sometime sometimes somewhat somewhere soon sorry specified specify specifying still sub such sup sure take taken taking tell tends th than thank thanks thanx that that'll thats that's that've the their theirs them themselves then thence there thereafter thereby there'd therefore therein there'll there're theres there's thereupon there've these they they'd they'll they're they've thing things think third thirty this thorough thoroughly those though three through throughout thru thus till to together too took toward towards tried tries truly try trying t's twice two un under underneath undoing unfortunately unless unlike unlikely until unto up upon upwards us use used useful uses using usually v value various versus very via viz vs want wants was wasn't way we we'd welcome well we'll went were we're weren't we've what whatever what'll what's what've when whence whenever where whereafter whereas whereby wherein where's whereupon wherever whether which whichever while whilst whither who who'd whoever whole who'll whom whomever who's whose why will willing wish with within without wonder won't would wouldn't yes yet you you'd you'll your you're yours yourself yourselves you've zero a how's i when's why's b c d e f g h j l m n o p q r s t u uucp w x y z I www amount bill bottom call computer con couldnt cry de describe detail due eleven empty fifteen fifty fill find fire forty front full give hasnt herse himse interest itse” mill move myse” part put show side sincere sixty system ten thick thin top twelve twenty abst accordance act added adopted affected affecting affects ah announce anymore apparently approximately aren arent arise auth beginning beginnings begins biol briefly ca date ed effect et-al ff fix gave giving heres hes hid home id im immediately importance important index information invention itd keys kg km largely lets line 'll means mg million ml mug na nay necessarily nos noted obtain obtained omitted ord owing page pages poorly possibly potentially pp predominantly present previously primarily promptly proud quickly ran readily ref refs related research resulted resulting results run sec section shed shes showed shown showns shows significant significantly similar similarly slightly somethan specifically state states stop strongly substantially successfully sufficiently suggest thered thereof therere thereto theyd theyre thou thoughh thousand throug til tip ts ups usefully usefulness 've vol vols wed whats wheres whim whod whos widely words world youd youre"
        stopWordsList = stopWordsStr.split(' ')
        bList = bStr.split(' ')
        array = array * len(bList)
        
        error=0
        
        #Removing characters that are not exist in english alphabet.
        for i in range(len(bList)):
            for j in range(len(bList[i])):
                for n in range(97,123):
                    if (bList[i][j] == chr(n)):
                        error+=1
                        break;
                if(error!=1):
                    bList[i] = bList[i].replace(bList[i][j]," ")
                else:
                    error=0

        #Removing spaces and stop words.
        whileCounter=0
        while (whileCounter<len(bList)):
            bList[whileCounter] = bList[whileCounter].replace(" ", "")
            if (bList[whileCounter]==""):
                del bList[whileCounter]
                whileCounter-=1
            for j in range(len(stopWordsList)):
                if (bList[whileCounter] == stopWordsList[j]):
                    del bList[whileCounter]
                    whileCounter-=1
                    break;
            whileCounter+=1
        
        #Counting words.
        for i in range(len(bList)):
            if (dictionary1.get(bList[i],control1)==-1):
                dictionary1[bList[i]] = '+'
            else:
                dictionary1[bList[i]] += '+'
        #Placing the results in an array. Indexes of the array is equals to frequency of words. This helps me to sort frequency of words.
        for i in dictionary1:
            if (array[len(dictionary1[i])] == ""):
                array[len(dictionary1[i])] = str(len(dictionary1[i])) + (" " * (editing1- len(str(len(dictionary1[i]))))) + i
            else:
                array[len(dictionary1[i])] += '\n' + str(len(dictionary1[i])) + (" " * (editing1- len(str(len(dictionary1[i]))))) + i
        
        #This part does the same work that above part does. But this part takes much more time to execute. I will leave this here maybe you want to see.
        """
        whileCounter=0
        while (whileCounter<len(bList)):
            whileCounter2=whileCounter
            while (whileCounter2<len(bList)):
                if (whileCounter2!=whileCounter):
                    if (bList[whileCounter]==bList[whileCounter2]):
                        counter+=1
                        del bList[whileCounter2]
                        whileCounter2-=1
                whileCounter2+=1
            if (array[counter] == "" ):
                array[counter] = bList[whileCounter]
            else:
                array[counter] = array[counter] + ' \n ' + bList[whileCounter]
            counter = 1
            whileCounter+=1
        """
        #Placing results into a result variable.
        #You can write how many different frequencies you want to see at the result by changing untilWhat variable. If you want to see all frequencies you can delete 82-83-87-88-89th statments.
        untilWhat=100
        topHundredCounter=0
        for i in range(len(array)-1,-1,-1):
            if (array[i]!=""):
                result = result + array[i] + '\n'
                topHundredCounter+=1
            if (topHundredCounter == untilWhat):
                break;
        
        #Assigning headers to variables.
        header1="| WORD     | WORD                | \n"
        header2="| ORDER    | ORDER               | \n"
        header3="| FREQUENCY| SEQUENCE            | \n"
        header4="---------------------------------- \n"
        
        #Writing results to output file.
        c = open(output,'w')
        c.write(header1)
        c.write(header2)
        c.write(header3)
        c.write(header4)
        c.write(result)
        c.close()
        
        
        
    elif(wordOrder == 2):
        a = open(book,'r', encoding="utf8")
        bStr = a.read()
        a.close()
        bStr = bStr.lower()
        #Replacing new line character with space character.
        bStr = bStr.replace("\n"," ")
        stopWordsStr = "able about above abroad according accordingly across actually adj after afterwards again against ago ahead ain't all allow allows almost alone along alongside already also although always am amid amidst among amongst an and another any anybody anyhow anyone anything anyway anyways anywhere apart appear appreciate appropriate are aren't around as a's aside ask asking associated at available away awfully back backward backwards be became because become becomes becoming been before beforehand begin behind being believe below beside besides best better between beyond both brief but by came can cannot cant can't caption cause causes certain certainly changes clearly c'mon co co. com come comes concerning consequently consider considering contain containing contains corresponding could couldn't course c's currently dare daren't definitely described despite did didn't different directly do does doesn't doing done don't down downwards during each edu eg eight eighty either else elsewhere end ending enough entirely especially et etc even ever evermore every everybody everyone everything everywhere ex exactly example except fairly far farther few fewer fifth first five followed following follows for forever former formerly forth forward found four from further furthermore get gets getting given gives go goes going gone got gotten greetings had hadn't half happens hardly has hasn't have haven't having he he'd he'll hello help hence her here hereafter hereby herein here's hereupon hers herself he's hi him himself his hither hopefully how howbeit however hundred i'd ie if ignored i'll i'm immediate in inasmuch inc inc. indeed indicate indicated indicates inner inside insofar instead into inward is isn't it it'd it'll its it's itself i've just k keep keeps kept know known knows last lately later latter latterly least less lest let let's like liked likely likewise little look looking looks low lower ltd made mainly make makes many may maybe mayn't me mean meantime meanwhile merely might mightn't mine minus miss more moreover most mostly mr mrs much must mustn't my myself name namely nd near nearly necessary need needn't needs neither never neverf neverless nevertheless new next nine ninety no nobody non none nonetheless noone no-one nor normally not nothing notwithstanding novel now nowhere obviously of off often oh ok okay old on once one ones one's only onto opposite or other others otherwise ought oughtn't our ours ourselves out outside over overall own particular particularly past per perhaps placed please plus possible presumably probably provided provides que quite qv rather rd re really reasonably recent recently regarding regardless regards relatively respectively right round said same saw say saying says second secondly see seeing seem seemed seeming seems seen self selves sensible sent serious seriously seven several shall shan't she she'd she'll she's should shouldn't since six so some somebody someday somehow someone something sometime sometimes somewhat somewhere soon sorry specified specify specifying still sub such sup sure take taken taking tell tends th than thank thanks thanx that that'll thats that's that've the their theirs them themselves then thence there thereafter thereby there'd therefore therein there'll there're theres there's thereupon there've these they they'd they'll they're they've thing things think third thirty this thorough thoroughly those though three through throughout thru thus till to together too took toward towards tried tries truly try trying t's twice two un under underneath undoing unfortunately unless unlike unlikely until unto up upon upwards us use used useful uses using usually v value various versus very via viz vs want wants was wasn't way we we'd welcome well we'll went were we're weren't we've what whatever what'll what's what've when whence whenever where whereafter whereas whereby wherein where's whereupon wherever whether which whichever while whilst whither who who'd whoever whole who'll whom whomever who's whose why will willing wish with within without wonder won't would wouldn't yes yet you you'd you'll your you're yours yourself yourselves you've zero a how's i when's why's b c d e f g h j l m n o p q r s t u uucp w x y z I www amount bill bottom call computer con couldnt cry de describe detail due eleven empty fifteen fifty fill find fire forty front full give hasnt herse himse interest itse” mill move myse” part put show side sincere sixty system ten thick thin top twelve twenty abst accordance act added adopted affected affecting affects ah announce anymore apparently approximately aren arent arise auth beginning beginnings begins biol briefly ca date ed effect et-al ff fix gave giving heres hes hid home id im immediately importance important index information invention itd keys kg km largely lets line 'll means mg million ml mug na nay necessarily nos noted obtain obtained omitted ord owing page pages poorly possibly potentially pp predominantly present previously primarily promptly proud quickly ran readily ref refs related research resulted resulting results run sec section shed shes showed shown showns shows significant significantly similar similarly slightly somethan specifically state states stop strongly substantially successfully sufficiently suggest thered thereof therere thereto theyd theyre thou thoughh thousand throug til tip ts ups usefully usefulness 've vol vols wed whats wheres whim whod whos widely words world youd youre"
        stopWordsList = stopWordsStr.split(' ')
        bList = bStr.split(' ')
        array = array * len(bList)
        
        #Removing characters that are not exist in english alphabet.
        error=0
        for i in range(len(bList)):
            for j in range(len(bList[i])):
                for n in range(97,123):
                    if (bList[i][j] == chr(n)):
                        error+=1
                        break;
                if(error!=1):
                    bList[i] = bList[i].replace(bList[i][j]," ")
                else:
                    error=0

        #Removing spaces and stop words.
        whileCounter=0
        while (whileCounter<len(bList)):
            bList[whileCounter] = bList[whileCounter].replace(" ", "")
            if (bList[whileCounter]==""):
                del bList[whileCounter]
                whileCounter-=1
            else:
                for j in range(len(stopWordsList)):
                    if (bList[whileCounter] == stopWordsList[j]):
                        del bList[whileCounter]
                        whileCounter-=1
                        break;
            
            whileCounter+=1
        
        #Counting words.
        for i in range(len(bList)-1):
            check1 = bList[i] + " " + bList[i+1]
            if (dictionary1.get(check1,control1)==-1):
                dictionary1[check1] = '+'
            else:
                dictionary1[check1] += '+'
                
        #Placing the results in an array. Indexes of the array is equals to frequency of words. This helps me to sort frequency of words.
        for i in dictionary1:
            if (array[len(dictionary1[i])] == ""):
                array[len(dictionary1[i])] = str(len(dictionary1[i])) + (" " * (editing1- len(str(len(dictionary1[i]))))) + i
            else:
                array[len(dictionary1[i])] += '\n' + str(len(dictionary1[i])) + (" " * (editing1- len(str(len(dictionary1[i]))))) + i
                
        #Placing results into a result variable.
        #You can write how many different frequencies you want to see at the result by changing untilWhat variable. If you want to see all frequencies you can delete 166-167-171-172-173th statments. 
        untilWhat=100
        topHundredCounter=0
        for i in range(len(array)-1,-1,-1):
            if (array[i]!=""):
                result = result + array[i] +'\n'
                topHundredCounter+=1
            if (topHundredCounter == untilWhat):
                break;
        
        #Assigning headers to variables.
        header1="| WORD     | WORD                | \n"
        header2="| ORDER    | ORDER               | \n"
        header3="| FREQUENCY| SEQUENCE            | \n"
        header4="---------------------------------- \n"
        
        #Writing results to output file.
        c = open(output,'w')
        c.write(header1)
        c.write(header2)
        c.write(header3)
        c.write(header4)
        c.write(result)
        c.close()
             
        
        
def Word_Order_Frequency_Two_Books(book1, book2, wordOrder1, result1):
    
    
    array=[""]
    result=""
    dictionary1={}
    control1=-1
    editing1=13
    
    
    
    if (wordOrder1 == 1):      
        a = open(book1,'r', encoding="utf8")
        bStr = a.read()
        a.close()
        bStr = bStr.lower()
        #Replacing new line character with space character.
        bStr = bStr.replace("\n"," ")
        stopWordsStr = "able about above abroad according accordingly across actually adj after afterwards again against ago ahead ain't all allow allows almost alone along alongside already also although always am amid amidst among amongst an and another any anybody anyhow anyone anything anyway anyways anywhere apart appear appreciate appropriate are aren't around as a's aside ask asking associated at available away awfully back backward backwards be became because become becomes becoming been before beforehand begin behind being believe below beside besides best better between beyond both brief but by came can cannot cant can't caption cause causes certain certainly changes clearly c'mon co co. com come comes concerning consequently consider considering contain containing contains corresponding could couldn't course c's currently dare daren't definitely described despite did didn't different directly do does doesn't doing done don't down downwards during each edu eg eight eighty either else elsewhere end ending enough entirely especially et etc even ever evermore every everybody everyone everything everywhere ex exactly example except fairly far farther few fewer fifth first five followed following follows for forever former formerly forth forward found four from further furthermore get gets getting given gives go goes going gone got gotten greetings had hadn't half happens hardly has hasn't have haven't having he he'd he'll hello help hence her here hereafter hereby herein here's hereupon hers herself he's hi him himself his hither hopefully how howbeit however hundred i'd ie if ignored i'll i'm immediate in inasmuch inc inc. indeed indicate indicated indicates inner inside insofar instead into inward is isn't it it'd it'll its it's itself i've just k keep keeps kept know known knows last lately later latter latterly least less lest let let's like liked likely likewise little look looking looks low lower ltd made mainly make makes many may maybe mayn't me mean meantime meanwhile merely might mightn't mine minus miss more moreover most mostly mr mrs much must mustn't my myself name namely nd near nearly necessary need needn't needs neither never neverf neverless nevertheless new next nine ninety no nobody non none nonetheless noone no-one nor normally not nothing notwithstanding novel now nowhere obviously of off often oh ok okay old on once one ones one's only onto opposite or other others otherwise ought oughtn't our ours ourselves out outside over overall own particular particularly past per perhaps placed please plus possible presumably probably provided provides que quite qv rather rd re really reasonably recent recently regarding regardless regards relatively respectively right round said same saw say saying says second secondly see seeing seem seemed seeming seems seen self selves sensible sent serious seriously seven several shall shan't she she'd she'll she's should shouldn't since six so some somebody someday somehow someone something sometime sometimes somewhat somewhere soon sorry specified specify specifying still sub such sup sure take taken taking tell tends th than thank thanks thanx that that'll thats that's that've the their theirs them themselves then thence there thereafter thereby there'd therefore therein there'll there're theres there's thereupon there've these they they'd they'll they're they've thing things think third thirty this thorough thoroughly those though three through throughout thru thus till to together too took toward towards tried tries truly try trying t's twice two un under underneath undoing unfortunately unless unlike unlikely until unto up upon upwards us use used useful uses using usually v value various versus very via viz vs want wants was wasn't way we we'd welcome well we'll went were we're weren't we've what whatever what'll what's what've when whence whenever where whereafter whereas whereby wherein where's whereupon wherever whether which whichever while whilst whither who who'd whoever whole who'll whom whomever who's whose why will willing wish with within without wonder won't would wouldn't yes yet you you'd you'll your you're yours yourself yourselves you've zero a how's i when's why's b c d e f g h j l m n o p q r s t u uucp w x y z I www amount bill bottom call computer con couldnt cry de describe detail due eleven empty fifteen fifty fill find fire forty front full give hasnt herse himse interest itse” mill move myse” part put show side sincere sixty system ten thick thin top twelve twenty abst accordance act added adopted affected affecting affects ah announce anymore apparently approximately aren arent arise auth beginning beginnings begins biol briefly ca date ed effect et-al ff fix gave giving heres hes hid home id im immediately importance important index information invention itd keys kg km largely lets line 'll means mg million ml mug na nay necessarily nos noted obtain obtained omitted ord owing page pages poorly possibly potentially pp predominantly present previously primarily promptly proud quickly ran readily ref refs related research resulted resulting results run sec section shed shes showed shown showns shows significant significantly similar similarly slightly somethan specifically state states stop strongly substantially successfully sufficiently suggest thered thereof therere thereto theyd theyre thou thoughh thousand throug til tip ts ups usefully usefulness 've vol vols wed whats wheres whim whod whos widely words world youd youre"
        stopWordsList = stopWordsStr.split(' ')
        bList = bStr.split(' ')
        array = array * len(bList)
        
        #Removing characters that are not exist in english alphabet.
        error=0
        for i in range(len(bList)):
            for j in range(len(bList[i])):
                for n in range(97,123):
                    if (bList[i][j] == chr(n)):
                        error+=1
                        break;
                if(error!=1):
                    bList[i] = bList[i].replace(bList[i][j]," ")
                else:
                    error=0

        #Removing spaces and stop words.
        whileCounter=0
        while (whileCounter<len(bList)):
            bList[whileCounter] = bList[whileCounter].replace(" ", "")
            if (bList[whileCounter]==""):
                del bList[whileCounter]
                whileCounter-=1
            for j in range(len(stopWordsList)):
                if (bList[whileCounter] == stopWordsList[j]):
                    del bList[whileCounter]
                    whileCounter-=1
                    break;
            whileCounter+=1
        
        #Counting words of the first file.
        for i in range(len(bList)):
            if (dictionary1.get(bList[i],control1)==-1):
                dictionary1[bList[i]] = '+'
            else:
                dictionary1[bList[i]] += '+'
        
        
        
        a = open(book2,'r', encoding="utf8")
        b2Str = a.read()
        a.close()
        b2Str = b2Str.lower()
        #Replacing new line character with space character.
        b2Str = b2Str.replace("\n"," ")
        stopWordsStr = "able about above abroad according accordingly across actually adj after afterwards again against ago ahead ain't all allow allows almost alone along alongside already also although always am amid amidst among amongst an and another any anybody anyhow anyone anything anyway anyways anywhere apart appear appreciate appropriate are aren't around as a's aside ask asking associated at available away awfully back backward backwards be became because become becomes becoming been before beforehand begin behind being believe below beside besides best better between beyond both brief but by came can cannot cant can't caption cause causes certain certainly changes clearly c'mon co co. com come comes concerning consequently consider considering contain containing contains corresponding could couldn't course c's currently dare daren't definitely described despite did didn't different directly do does doesn't doing done don't down downwards during each edu eg eight eighty either else elsewhere end ending enough entirely especially et etc even ever evermore every everybody everyone everything everywhere ex exactly example except fairly far farther few fewer fifth first five followed following follows for forever former formerly forth forward found four from further furthermore get gets getting given gives go goes going gone got gotten greetings had hadn't half happens hardly has hasn't have haven't having he he'd he'll hello help hence her here hereafter hereby herein here's hereupon hers herself he's hi him himself his hither hopefully how howbeit however hundred i'd ie if ignored i'll i'm immediate in inasmuch inc inc. indeed indicate indicated indicates inner inside insofar instead into inward is isn't it it'd it'll its it's itself i've just k keep keeps kept know known knows last lately later latter latterly least less lest let let's like liked likely likewise little look looking looks low lower ltd made mainly make makes many may maybe mayn't me mean meantime meanwhile merely might mightn't mine minus miss more moreover most mostly mr mrs much must mustn't my myself name namely nd near nearly necessary need needn't needs neither never neverf neverless nevertheless new next nine ninety no nobody non none nonetheless noone no-one nor normally not nothing notwithstanding novel now nowhere obviously of off often oh ok okay old on once one ones one's only onto opposite or other others otherwise ought oughtn't our ours ourselves out outside over overall own particular particularly past per perhaps placed please plus possible presumably probably provided provides que quite qv rather rd re really reasonably recent recently regarding regardless regards relatively respectively right round said same saw say saying says second secondly see seeing seem seemed seeming seems seen self selves sensible sent serious seriously seven several shall shan't she she'd she'll she's should shouldn't since six so some somebody someday somehow someone something sometime sometimes somewhat somewhere soon sorry specified specify specifying still sub such sup sure take taken taking tell tends th than thank thanks thanx that that'll thats that's that've the their theirs them themselves then thence there thereafter thereby there'd therefore therein there'll there're theres there's thereupon there've these they they'd they'll they're they've thing things think third thirty this thorough thoroughly those though three through throughout thru thus till to together too took toward towards tried tries truly try trying t's twice two un under underneath undoing unfortunately unless unlike unlikely until unto up upon upwards us use used useful uses using usually v value various versus very via viz vs want wants was wasn't way we we'd welcome well we'll went were we're weren't we've what whatever what'll what's what've when whence whenever where whereafter whereas whereby wherein where's whereupon wherever whether which whichever while whilst whither who who'd whoever whole who'll whom whomever who's whose why will willing wish with within without wonder won't would wouldn't yes yet you you'd you'll your you're yours yourself yourselves you've zero a how's i when's why's b c d e f g h j l m n o p q r s t u uucp w x y z I www amount bill bottom call computer con couldnt cry de describe detail due eleven empty fifteen fifty fill find fire forty front full give hasnt herse himse interest itse” mill move myse” part put show side sincere sixty system ten thick thin top twelve twenty abst accordance act added adopted affected affecting affects ah announce anymore apparently approximately aren arent arise auth beginning beginnings begins biol briefly ca date ed effect et-al ff fix gave giving heres hes hid home id im immediately importance important index information invention itd keys kg km largely lets line 'll means mg million ml mug na nay necessarily nos noted obtain obtained omitted ord owing page pages poorly possibly potentially pp predominantly present previously primarily promptly proud quickly ran readily ref refs related research resulted resulting results run sec section shed shes showed shown showns shows significant significantly similar similarly slightly somethan specifically state states stop strongly substantially successfully sufficiently suggest thered thereof therere thereto theyd theyre thou thoughh thousand throug til tip ts ups usefully usefulness 've vol vols wed whats wheres whim whod whos widely words world youd youre"
        stopWordsList = stopWordsStr.split(' ')
        b2List = b2Str.split(' ')
        
        #Removing characters that are not exist in english alphabet.
        error=0
        for i in range(len(b2List)):
            for j in range(len(b2List[i])):
                for n in range(97,123):
                    if (b2List[i][j] == chr(n)):
                        error+=1
                        break;
                if(error!=1):
                    b2List[i] = b2List[i].replace(b2List[i][j]," ")
                else:
                    error=0

        #Removing spaces and stop words.
        whileCounter=0
        while (whileCounter<len(b2List)):
            b2List[whileCounter] = b2List[whileCounter].replace(" ", "")
            if (b2List[whileCounter]==""):
                del b2List[whileCounter]
                whileCounter-=1
            for j in range(len(stopWordsList)):
                if (b2List[whileCounter] == stopWordsList[j]):
                    del b2List[whileCounter]
                    whileCounter-=1
                    break;
            whileCounter+=1
        
        #Counting words of the second file.
        for i in range(len(b2List)):
            if (dictionary1.get(b2List[i],control1)==-1):
                dictionary1[b2List[i]] = '/'
            else:
                dictionary1[b2List[i]] += '/'
        
        #Placing the results in an array. Indexes of the array is equals to frequency of words. This helps me to sort frequency of words.
        for i in dictionary1:
            plusCounter=0
            slashCounter=0
            for j in range(len(dictionary1[i])):
               if (dictionary1[i][j] == '+'):
                   plusCounter+=1
               elif (dictionary1[i][j] == '/'):
                   slashCounter+=1
            if (array[len(dictionary1[i])] == ""):
                array[len(dictionary1[i])] = str(len(dictionary1[i])) + (" " * (editing1-len(str(len(dictionary1[i]))))) + str(plusCounter) + (" " * (editing1-len(str(plusCounter)))) + str(slashCounter) + (" " * (editing1-len(str(slashCounter)))) + i 
            else:
                array[len(dictionary1[i])] += '\n' + str(len(dictionary1[i])) + (" " * (editing1-len(str(len(dictionary1[i]))))) + str(plusCounter) + (" " * (editing1-len(str(plusCounter)))) + str(slashCounter) + (" " * (editing1-len(str(slashCounter)))) + i 
        
        #Placing results into a result variable.
        #You can write how many different frequencies you want to see at the result by changing untilWhat variable. If you want to see all frequencies you can delete 311-312-316-317-318th statments.
        untilWhat=100
        topHundredCounter=0
        for i in range(len(array)-1,-1,-1):
            if (array[i]!=""):
                result = result + array[i] + '\n'
                topHundredCounter+=1
            if (topHundredCounter == untilWhat):
                break;
        
        #Assigning headers to variables.
        header1="| TOTAL     | BOOK 1    | BOOK 2    | WORD                |  \n"
        header2="| ORDER     | ORDER     | ORDER     | ORDER               |  \n"
        header3="| FREQUENCY | FREQUENCY | FREQUENCY | SEQUENCE            |  \n"
        header4="-----------------------------------------------------------  \n"
        
        #Writing results to output file.
        c = open(output,'w')
        c.write(header1)
        c.write(header2)
        c.write(header3)
        c.write(header4)
        c.write(result)
        c.close()
    
    
    
    
    
    
    elif(wordOrder1 == 2):
        a = open(book1,'r', encoding="utf8")
        bStr = a.read()
        a.close()
        bStr = bStr.lower()
        #Replacing new line character with space character.
        bStr = bStr.replace("\n"," ")
        stopWordsStr = "able about above abroad according accordingly across actually adj after afterwards again against ago ahead ain't all allow allows almost alone along alongside already also although always am amid amidst among amongst an and another any anybody anyhow anyone anything anyway anyways anywhere apart appear appreciate appropriate are aren't around as a's aside ask asking associated at available away awfully back backward backwards be became because become becomes becoming been before beforehand begin behind being believe below beside besides best better between beyond both brief but by came can cannot cant can't caption cause causes certain certainly changes clearly c'mon co co. com come comes concerning consequently consider considering contain containing contains corresponding could couldn't course c's currently dare daren't definitely described despite did didn't different directly do does doesn't doing done don't down downwards during each edu eg eight eighty either else elsewhere end ending enough entirely especially et etc even ever evermore every everybody everyone everything everywhere ex exactly example except fairly far farther few fewer fifth first five followed following follows for forever former formerly forth forward found four from further furthermore get gets getting given gives go goes going gone got gotten greetings had hadn't half happens hardly has hasn't have haven't having he he'd he'll hello help hence her here hereafter hereby herein here's hereupon hers herself he's hi him himself his hither hopefully how howbeit however hundred i'd ie if ignored i'll i'm immediate in inasmuch inc inc. indeed indicate indicated indicates inner inside insofar instead into inward is isn't it it'd it'll its it's itself i've just k keep keeps kept know known knows last lately later latter latterly least less lest let let's like liked likely likewise little look looking looks low lower ltd made mainly make makes many may maybe mayn't me mean meantime meanwhile merely might mightn't mine minus miss more moreover most mostly mr mrs much must mustn't my myself name namely nd near nearly necessary need needn't needs neither never neverf neverless nevertheless new next nine ninety no nobody non none nonetheless noone no-one nor normally not nothing notwithstanding novel now nowhere obviously of off often oh ok okay old on once one ones one's only onto opposite or other others otherwise ought oughtn't our ours ourselves out outside over overall own particular particularly past per perhaps placed please plus possible presumably probably provided provides que quite qv rather rd re really reasonably recent recently regarding regardless regards relatively respectively right round said same saw say saying says second secondly see seeing seem seemed seeming seems seen self selves sensible sent serious seriously seven several shall shan't she she'd she'll she's should shouldn't since six so some somebody someday somehow someone something sometime sometimes somewhat somewhere soon sorry specified specify specifying still sub such sup sure take taken taking tell tends th than thank thanks thanx that that'll thats that's that've the their theirs them themselves then thence there thereafter thereby there'd therefore therein there'll there're theres there's thereupon there've these they they'd they'll they're they've thing things think third thirty this thorough thoroughly those though three through throughout thru thus till to together too took toward towards tried tries truly try trying t's twice two un under underneath undoing unfortunately unless unlike unlikely until unto up upon upwards us use used useful uses using usually v value various versus very via viz vs want wants was wasn't way we we'd welcome well we'll went were we're weren't we've what whatever what'll what's what've when whence whenever where whereafter whereas whereby wherein where's whereupon wherever whether which whichever while whilst whither who who'd whoever whole who'll whom whomever who's whose why will willing wish with within without wonder won't would wouldn't yes yet you you'd you'll your you're yours yourself yourselves you've zero a how's i when's why's b c d e f g h j l m n o p q r s t u uucp w x y z I www amount bill bottom call computer con couldnt cry de describe detail due eleven empty fifteen fifty fill find fire forty front full give hasnt herse himse interest itse” mill move myse” part put show side sincere sixty system ten thick thin top twelve twenty abst accordance act added adopted affected affecting affects ah announce anymore apparently approximately aren arent arise auth beginning beginnings begins biol briefly ca date ed effect et-al ff fix gave giving heres hes hid home id im immediately importance important index information invention itd keys kg km largely lets line 'll means mg million ml mug na nay necessarily nos noted obtain obtained omitted ord owing page pages poorly possibly potentially pp predominantly present previously primarily promptly proud quickly ran readily ref refs related research resulted resulting results run sec section shed shes showed shown showns shows significant significantly similar similarly slightly somethan specifically state states stop strongly substantially successfully sufficiently suggest thered thereof therere thereto theyd theyre thou thoughh thousand throug til tip ts ups usefully usefulness 've vol vols wed whats wheres whim whod whos widely words world youd youre"
        stopWordsList = stopWordsStr.split(' ')
        bList = bStr.split(' ')
        array = array * len(bList) 
        
        #Removing characters that are not exist in english alphabet.
        error=0
        for i in range(len(bList)):
            for j in range(len(bList[i])):
                for n in range(97,123):
                    if (bList[i][j] == chr(n)):
                        error+=1
                        break;
                if(error!=1):
                    bList[i] = bList[i].replace(bList[i][j]," ")
                else:
                    error=0

        #Removing spaces and stop words.
        whileCounter=0
        while (whileCounter<len(bList)):
            bList[whileCounter] = bList[whileCounter].replace(" ", "")
            if (bList[whileCounter]==""):
                del bList[whileCounter]
                whileCounter-=1
            else:
                for j in range(len(stopWordsList)):
                    if (bList[whileCounter] == stopWordsList[j]):
                        del bList[whileCounter]
                        whileCounter-=1
                        break;
            
            whileCounter+=1
        
        #Counting words of the first file.
        for i in range(len(bList)-1):
            check1 = bList[i] + " " + bList[i+1]
            if (dictionary1.get(check1,control1)==-1):
                dictionary1[check1] = '+'
            else:
                dictionary1[check1] += '+'
                
              
        a = open(book2,'r', encoding="utf8")
        b2Str = a.read()
        a.close()
        b2Str = b2Str.lower()
        #Replacing new line character with space character.
        b2Str = b2Str.replace("\n"," ")
        stopWordsStr = "able about above abroad according accordingly across actually adj after afterwards again against ago ahead ain't all allow allows almost alone along alongside already also although always am amid amidst among amongst an and another any anybody anyhow anyone anything anyway anyways anywhere apart appear appreciate appropriate are aren't around as a's aside ask asking associated at available away awfully back backward backwards be became because become becomes becoming been before beforehand begin behind being believe below beside besides best better between beyond both brief but by came can cannot cant can't caption cause causes certain certainly changes clearly c'mon co co. com come comes concerning consequently consider considering contain containing contains corresponding could couldn't course c's currently dare daren't definitely described despite did didn't different directly do does doesn't doing done don't down downwards during each edu eg eight eighty either else elsewhere end ending enough entirely especially et etc even ever evermore every everybody everyone everything everywhere ex exactly example except fairly far farther few fewer fifth first five followed following follows for forever former formerly forth forward found four from further furthermore get gets getting given gives go goes going gone got gotten greetings had hadn't half happens hardly has hasn't have haven't having he he'd he'll hello help hence her here hereafter hereby herein here's hereupon hers herself he's hi him himself his hither hopefully how howbeit however hundred i'd ie if ignored i'll i'm immediate in inasmuch inc inc. indeed indicate indicated indicates inner inside insofar instead into inward is isn't it it'd it'll its it's itself i've just k keep keeps kept know known knows last lately later latter latterly least less lest let let's like liked likely likewise little look looking looks low lower ltd made mainly make makes many may maybe mayn't me mean meantime meanwhile merely might mightn't mine minus miss more moreover most mostly mr mrs much must mustn't my myself name namely nd near nearly necessary need needn't needs neither never neverf neverless nevertheless new next nine ninety no nobody non none nonetheless noone no-one nor normally not nothing notwithstanding novel now nowhere obviously of off often oh ok okay old on once one ones one's only onto opposite or other others otherwise ought oughtn't our ours ourselves out outside over overall own particular particularly past per perhaps placed please plus possible presumably probably provided provides que quite qv rather rd re really reasonably recent recently regarding regardless regards relatively respectively right round said same saw say saying says second secondly see seeing seem seemed seeming seems seen self selves sensible sent serious seriously seven several shall shan't she she'd she'll she's should shouldn't since six so some somebody someday somehow someone something sometime sometimes somewhat somewhere soon sorry specified specify specifying still sub such sup sure take taken taking tell tends th than thank thanks thanx that that'll thats that's that've the their theirs them themselves then thence there thereafter thereby there'd therefore therein there'll there're theres there's thereupon there've these they they'd they'll they're they've thing things think third thirty this thorough thoroughly those though three through throughout thru thus till to together too took toward towards tried tries truly try trying t's twice two un under underneath undoing unfortunately unless unlike unlikely until unto up upon upwards us use used useful uses using usually v value various versus very via viz vs want wants was wasn't way we we'd welcome well we'll went were we're weren't we've what whatever what'll what's what've when whence whenever where whereafter whereas whereby wherein where's whereupon wherever whether which whichever while whilst whither who who'd whoever whole who'll whom whomever who's whose why will willing wish with within without wonder won't would wouldn't yes yet you you'd you'll your you're yours yourself yourselves you've zero a how's i when's why's b c d e f g h j l m n o p q r s t u uucp w x y z I www amount bill bottom call computer con couldnt cry de describe detail due eleven empty fifteen fifty fill find fire forty front full give hasnt herse himse interest itse” mill move myse” part put show side sincere sixty system ten thick thin top twelve twenty abst accordance act added adopted affected affecting affects ah announce anymore apparently approximately aren arent arise auth beginning beginnings begins biol briefly ca date ed effect et-al ff fix gave giving heres hes hid home id im immediately importance important index information invention itd keys kg km largely lets line 'll means mg million ml mug na nay necessarily nos noted obtain obtained omitted ord owing page pages poorly possibly potentially pp predominantly present previously primarily promptly proud quickly ran readily ref refs related research resulted resulting results run sec section shed shes showed shown showns shows significant significantly similar similarly slightly somethan specifically state states stop strongly substantially successfully sufficiently suggest thered thereof therere thereto theyd theyre thou thoughh thousand throug til tip ts ups usefully usefulness 've vol vols wed whats wheres whim whod whos widely words world youd youre"
        stopWordsList = stopWordsStr.split(' ')
        b2List = b2Str.split(' ')
        
        #Removing characters that are not exist in english alphabet.
        error=0
        for i in range(len(b2List)):
            for j in range(len(b2List[i])):
                for n in range(97,123):
                    if (b2List[i][j] == chr(n)):
                        error+=1
                        break;
                if(error!=1):
                    b2List[i] = b2List[i].replace(b2List[i][j]," ")
                else:
                    error=0

        #Removing spaces and stop words.
        whileCounter=0
        while (whileCounter<len(b2List)):
            b2List[whileCounter] = b2List[whileCounter].replace(" ", "")
            if (b2List[whileCounter]==""):
                del b2List[whileCounter]
                whileCounter-=1
            else:
                for j in range(len(stopWordsList)):
                    if (b2List[whileCounter] == stopWordsList[j]):
                        del b2List[whileCounter]
                        whileCounter-=1
                        break;
            
            whileCounter+=1
        
        #Counting words of the second file.
        for i in range(len(b2List)-1):
            check1 = b2List[i] + " " + b2List[i+1]
            if (dictionary1.get(check1,control1)==-1):
                dictionary1[check1] = '/'
            else:
                dictionary1[check1] += '/'
                
        #Placing the results in an array. Indexes of the array is equals to frequency of words. This helps me to sort frequency of words.
        for i in dictionary1:
            plusCounter=0
            slashCounter=0
            for j in range(len(dictionary1[i])):
               if (dictionary1[i][j] == '+'):
                   plusCounter+=1
               elif (dictionary1[i][j] == '/'):
                   slashCounter+=1
            if (array[len(dictionary1[i])] == ""):
                array[len(dictionary1[i])] = str(len(dictionary1[i])) + (" " * (editing1-len(str(len(dictionary1[i]))))) + str(plusCounter) + (" " * (editing1-len(str(plusCounter)))) + str(slashCounter) + (" " * (editing1-len(str(slashCounter)))) + i 
            else:
                array[len(dictionary1[i])] += '\n' + str(len(dictionary1[i])) + (" " * (editing1-len(str(len(dictionary1[i]))))) + str(plusCounter) + (" " * (editing1-len(str(plusCounter)))) + str(slashCounter) + (" " * (editing1-len(str(slashCounter)))) + i 
            
        #Placing results into a result variable.
        #You can write how many rows you want to see at the result by changing untilWhat variable. If you want to see all rows you can delete 453-454-458-459-460th statments. 
        untilWhat=100
        topHundredCounter=0
        for i in range(len(array)-1,-1,-1):
            if (array[i]!=""):
                result = result + array[i] +'\n'
                topHundredCounter+=1
            if (topHundredCounter == untilWhat):
                break;
        
        #Assigning headers to variables.
        header1="| TOTAL     | BOOK 1    | BOOK 2    | WORD                |  \n"
        header2="| ORDER     | ORDER     | ORDER     | ORDER               |  \n"
        header3="| FREQUENCY | FREQUENCY | FREQUENCY | SEQUENCE            |  \n"
        header4="-----------------------------------------------------------  \n"
        
        #Writing results to output file.
        c = open(output,'w')
        c.write(header1)
        c.write(header2)
        c.write(header3)
        c.write(header4)
        c.write(result)
        c.close()
    
    
    
#Choosing which function is going to be used and what is going to be functions parameters.
test1=0
whichFunction=int(input("Which function do you want to use? If you want to see frequency of words of just one book please write 1 on the other side if you want to see frequency of words of two books please write 2. :"))
while (test1==0):
    test1=1
    if (whichFunction == 1):
        file = input("Which file do you want to use: ")
        digit = int(input("Do you want to see one word length patterns or two word length patterns?  Just one and two accepted. If one please write 1 if two please write 2. : "))
        while(digit!=1 & digit!=2):
            digit = int(input("Please enter 1 or 2. :"))
        output = input("Please write the name of output file: ")
        test2=0
        while(test2==0):
            test2=1
            try:
                Word_Order_Frequency_One_Book(file,digit,output)
            except(FileNotFoundError):
                file = input("The file you wrote does not exist at the same directory with python file. Please write appropriate file name: ")
                test2=0
        input("This is for waiting.")
    elif (whichFunction == 2):
        file1 = input("Please enter the name of first file: ")
        file2 = input("Please enter the name of second file: ")
        digit = int(input("Do you want to see one word length patterns or two word length patterns?  Just one and two accepted. If one please write 1 if two please write 2. : "))
        while(digit!=1 & digit!=2):
            digit = int(input("Please enter 1 or 2. :"))
        output = input("Please write the name of output file: ")
        test2=0
        while(test2==0):
            test2=1
            try:
                Word_Order_Frequency_Two_Books(file1,file2,digit,output)
            except(FileNotFoundError):
                print("The file you wrote does not exist at the same directory with python file. Please write appropriate file names: ")
                file1 = input("Please enter the name of first file: ")
                file2 = input("Please enter the name of second file: ")
                test2=0
        input("This is for waiting.")
    else:
        whichFunction=int(input("Please write an appropriate input. Just 1 or 2 is accepted. :"))
        test1=0
    
    
    
    
    
    
    
    
    
    