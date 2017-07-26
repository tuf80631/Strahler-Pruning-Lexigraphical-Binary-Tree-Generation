//retruns strahler number for whichever node is specified by i in parameter  
   public int getStrahlerNumber(int i) {  
  int l ;  
  int r ;  
  if(left[i] != 0)  
   l = getStrahlerNumber(left[i]);  
  else l = 0;  
  if(right[i] != 0)  
   r = getStrahlerNumber(right[i]);  
  else r = 0;  
  if(l != r)  
  strahlerN = Math.max(l, r);  
  else strahlerN = l+1;  
  return strahlerN;  
 }  
   //finds the pruning number among trees in forest  
   public int findMaxPruningNumberOfForest()  
   {  
     int i = 1; int pruningNumber; int max = 0;  
     do  
     {  
       pruningNumber = findPruningNumber(i);  
       if (pruningNumber>max)  
       {  
         max = pruningNumber;  
       }  
       i = right[i];  
     }while (i!=0);  
     return max;  
   }  
  //returns pruning number of node k.  
   public int findPruningNumber(int k)  
   {  
     int i = left[k];  
     int pruningNumber; int max = 0; int tempMax = 0; boolean alreadyFoundDuplicate = false;  
     do {  
       if (left[i]!=0) pruningNumber = findPruningNumber(i);  
       else pruningNumber = 1;  
       if (pruningNumber>max)  
       {  
         max = pruningNumber;  
         tempMax = pruningNumber;  
         alreadyFoundDuplicate = false;  
       }  
       else if (pruningNumber==max && !alreadyFoundDuplicate)  
       {  
         tempMax++;  
         alreadyFoundDuplicate = true;  
       }  
       i = right[i];  
     } while (i!=0);  
     if (tempMax>max)max = tempMax;  
     return max;  
   }  