import React from "react";

const RewardProgressBar = ({
  reward,
  totalMatches,
  background,
  height,
  marginTop,
  style,
}) => {
  
  // totalMatches는 임의로 넣어논 값. 
  const percentage = (reward / totalMatches) * 100;
  return (
    <div
      className="w-full bg-white"
      style={{ height, ...style }}
    >
      <div
        style={{
          width: `${percentage}%`,
          height,
          marginTop,
          background,
        }}
      ></div>
    </div>
  );
};

export default RewardProgressBar;
