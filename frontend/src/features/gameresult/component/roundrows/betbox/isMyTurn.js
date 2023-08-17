export const isMyTurn = (meFirst, index) => {
  if(meFirst){
    return index % 2 === 0 ? true : false;
  }
  else{
    return index % 2 === 0 ? false : true;
  }
  
};
