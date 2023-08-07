import React from "react";
import Sheriff from "../../assets/images/myinfo/sheriff.svg";
import LineForMyInfo from "../../assets/images/myinfo/line-for-my-info.svg";
import TierBox from "@component/TierBox";
import Parchment from "@component/Parchment";
import MyInfoHeader from "./MyInfoHeader";
import MyInfoReward from "./MyInfoReward";
import MyInfoOdds from "./MyInfoOdds";

const MyInfo = ({
  width,
  height,
  tier,
  nickname,
  reward,
  totalMatches,
  wins,
  button,
}) => {
  return (
    <Parchment
      style={{
        width,
        height,
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <div className="flex mx-2 gap-3 items-center">
        <TierBox tier={tier} width="135px" height="135px" />
        <div className="mx-1">
          <MyInfoHeader nickname={nickname} button={button} />
          <img src={LineForMyInfo} alt="line for myInfo" />
          <div className="my-0">
            <MyInfoReward reward={reward} totalMatches={totalMatches} />
            <MyInfoOdds wins={wins} totalMatches={totalMatches} />
          </div>
          <img src={LineForMyInfo} alt="line for myInfo" />
        </div>
        <img
          src={Sheriff}
          style={{
            top: "110px",
            right: "110px",
            position: "absolute",
            zIndex: 1,
          }}
          alt="sheriff"
        />
      </div>
    </Parchment>
  );
};

export default MyInfo;
