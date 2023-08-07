import React from "react";
import RewardProgressBar from "./RewardProgressBar";

const MyInfoReward = ({ reward, totalMatches }) => {
  return (
    <div className="my-0 mx-3 gap-2 h-8 flex items-center">
      <span
        style={{
          fontFamily: "Unchained-Spaghetti",
          color: "gray",
          fontSize: "30px",
        }}
      >
        REWARD
      </span>
      <div className="w-full">
        <p
          style={{
            fontFamily: "Unchained-Spaghetti",
            fontSize: "24px",
          }}
        >
          $ {reward}
        </p>
        <RewardProgressBar
          style={{ position: "relative", zIndex: 2 }}
          reward={reward}
          totalMatches={totalMatches}
          height="8px"
          marginTop="-0.6rem"
          background="var(--red)"
        />
      </div>
    </div>
  );
};

export default MyInfoReward;
