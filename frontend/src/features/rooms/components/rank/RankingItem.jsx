import React from "react";
import TierNoBox from "../TierNoBox";

function RankingItem({ id, nickname, tier, reward, ranking }) {
  return (
    <div className="flex items-center justify-center">
      <div
        className="grid grid-cols-8 gap-2 items-center font-nexonGothic"
        style={{ width: "380px", height: "43px", fontSize: "30px" }}
      >
        <p className="inline-block text-center">#{ranking}</p>
        <div className="m-auto">
          <TierNoBox tier={tier} />
        </div>
        <p className="col-span-4">{nickname}</p>
        <p className="col-span-2">${reward}</p>
      </div>
    </div>
  );
}

export default RankingItem;
