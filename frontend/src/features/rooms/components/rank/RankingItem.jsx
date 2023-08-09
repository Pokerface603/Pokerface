import React from "react";
import TierNoBox from "../TierNoBox";

function RankingItem({ id, nickname, tier, reward }) {
  const isenglish = /^[A-Za-z]+$/;

  const fontFamily = isenglish.test(nickname)
    ? "Unchained-Spaghetti"
    : "NexonGothic";

  return (
    <div className="flex items-center justify-center">
      <div
        className="grid grid-cols-8 gap-2 items-center"
        style={{ width: "380px", height: "43px" }}
      >
        <p
          className="inline-block text-center"
          style={{ fontFamily, fontSize: "30px" }}
        >
          #1
        </p>
        <div className="m-auto">
          <TierNoBox tier={tier} />
        </div>
        <p className="col-span-4" style={{ fontFamily, fontSize: "30px" }}>
          {nickname}
        </p>
        <p className="col-span-2" style={{ fontFamily, fontSize: "30px" }}>
          ${reward}
        </p>
      </div>
    </div>
  );
}

export default RankingItem;
