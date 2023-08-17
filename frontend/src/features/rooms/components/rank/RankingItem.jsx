import React from "react";
import TierNoBox from "../TierNoBox";

function RankingItem({ id, nickname, tier, reward, ranking }) {
  const formattedReward = reward.toLocaleString();

  const isenglish = /^[A-Za-z]+$/;

  const fontFamily = isenglish.test(nickname)
    ? "Unchained-Spaghetti"
    : "NexonGothic";

  let fontSize = "28px"; // 기본 폰트 사이즈
  if (
    nickname &&
    !isenglish.test(nickname) &&
    nickname.length > 5 &&
    nickname.length < 9
  ) {
    fontSize = "22px"; // 길이가 5보다 크고 9보다 작을 때 폰트 사이즈를 20으로 변경
  }
  return (
    <div className="flex items-center justify-center">
      <div
        className="grid grid-cols-9 gap-1 items-center font-nexonGothic"
        style={{ width: "380px", height: "43px", fontSize: "30px" }}
      >
        <p
          className="inline-block text-center"
          style={{ fontFamily: "Unchained-Spaghetti" }}
        >
          #{ranking}
        </p>
        <div className="m-auto">
          <TierNoBox tier={tier} />
        </div>
        <p className="col-span-4" style={{ fontFamily, fontSize }}>
          {nickname}
        </p>
        <p
          className="col-span-3"
          style={{
            fontFamily: "Unchained-Spaghetti",
          }}
        >
          $ {formattedReward}
        </p>
      </div>
    </div>
  );
}

export default RankingItem;
