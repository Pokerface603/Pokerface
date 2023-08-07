import TierBox from "@component/TierBox";
import WoodBackground from "@component/WoodBackground";
import WoodButton from "@component/WoodButton";
import React from "react";
import MatchHistoryTable from "./MatchHistoryTable";
import Parchment from "@component/Parchment";
import HistorySummary from "./HistorySummary";

const MyPage = () => {
  return (
    <WoodBackground>
      {/* 좌측 */}
      <div
        className="grid grid-cols-1 grid-rows-[290px,auto] mr-6 content-stretch justify-center"
        style={{ width: "1000px", height: "880px" }}>
        <div className="w-full h-full flex bg-blue-400">
          <TierBox tier="J" width="455px" height="210px" />
          <WoodButton
            className="skew-x-6"
            width="323px"
            height="58px"
            fontSize="50px"
            text="Back"
          />
        </div>
        <div className="w-full h-full flex items-center overflow-hidden">
          <MatchHistoryTable />
        </div>
      </div>
      {/* 우측 */}
      <div
        className="grid grid-cols-1 grid-rows-[400px,auto] ml-4 content-stretch justify-center items-center"
        style={{ width: "800px", height: "880px" }}>
        <div className="w-full h-2/3 flex bg-blue-400">
          <HistorySummary />
        </div>
        <div className="w-full h-full bg-blue-900"></div>
      </div>
    </WoodBackground>
  );
};

export default MyPage;
