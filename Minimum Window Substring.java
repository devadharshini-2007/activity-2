lass Solution {
    public String minWindow(String s, String t) {
        int left=0;//LEFT OF WINDOW
        int right=0;//RIGHT OF WINDOW
        int freq[]=new int[56];//WINDOWSFREQ
        int tfreq[]=new int[56];// TARGET FREQUENCY
        for(int i=0;i<t.length();i++){//BUILDING TARGET FREQUENCY
            char ch=t.charAt(i);
            if(ch>='a'&&ch<='z'){
                tfreq[ch-'a'+26]++;
            }else{
                tfreq[ch-'A']++;
            }
        }
        int required=0;// TOTAL UNIQUE CHARS
        int formed=0;
        int minlength=Integer.MAX_VALUE;
        int startindex=0;
        for(int i=0;i<56;i++){ //COUNTING UNIQUE CHARS
            if(tfreq[i]>0){
                required++;
            }
        }
        //SLIDING WINDOW
        while(right<s.length()){
           char ch=s.charAt(right);
           int index=(ch>='a'&&ch<='z')?ch-'a'+26:ch-'A';
        //    if(ch>='a'&&ch<='z'){
        //     freq[ch-'a'+26]++;
        //    }else{
        //     freq[ch-'A']++;
        //    }
        freq[index]++;
           // IF FREQ  GETS MATCHES CHARS FORMED
           if(tfreq[index]>0&&freq[index]==tfreq[index])formed++;
           // IF ENTIRE CHARS WAS PRESENT THEN ENTER IN UPDATING MIN AND SHRINKING PART
          while(formed==required){
            //UPDATING MIN  IF CURRENT WINDOW IS MINIMUM
            if(right-left+1<minlength){
                minlength=right-left+1;
                startindex=left;
            }
            //SHRINKING FROM LEFT
            char leftchar=s.charAt(left);
            int leftindex=(leftchar>='a'&&leftchar<='z')?leftchar-'a'+26:leftchar-'A';
            // if(leftchar>='a'&&leftchar<='z'){
            //     freq[leftchar-'a'+26]--;
            // }else{
            //     freq[leftchar-'A']--;
            // }
            freq[leftindex]--;  
            //IF AFTER SHRINKING THE CHARACTERS MISSED THEN REDUCING FORMED
            if(tfreq[leftindex]>0&&freq[leftindex]<tfreq[leftindex])formed--;
            left++;  
          }
          right++;
        }
        return(minlength==Integer.MAX_VALUE)?"":s.substring(startindex,startindex+minlength);
       
    }
}
