import WoodBackground from "@component/WoodBackground";
import React, { useEffect, useState } from "react";
import MatchHistoryTable from "./MatchHistoryTable";
import HistorySummary from "./HistorySummary";
import MyInfo from "../../../components/myinfo/MyInfo";
import BackButton from "./BackButton";
import { useSelector } from "react-redux";
import { getTotalRecord } from "../api/getTotalRecord";

const MyPage = () => {
  const [totalData, setTotalData] = useState(null);
  const nickname = useSelector((state) => state.user.nickname);
  const email = useSelector((state) => state.user.email);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getTotalRecord(email);
        setTotalData(data);
      } catch (error) {
        console.log("error", error);
      }
    };
    fetchData();
  }, [email]);

  return (
    <WoodBackground>
      <div
        className="grid grid-cols-1 grid-rows-[190px,auto] mr-6 content-stretch justify-center"
        style={{ width: "1000px", height: "880px" }}>
        <div className="w-full h-full flex">
          <div className="mt-4">
            {totalData !== null && (
              <MyInfo
                width="455px"
                height="160px"
                tier={totalData.tier}
                nickname={nickname}
                reward={totalData.rating}
                totalMatches={totalData.totalCount}
                wins={totalData.winCount}
              />
            )}
          </div>
          <BackButton />
        </div>
        <div className="w-full h-full flex items-center overflow-hidden">
          <MatchHistoryTable />
        </div>
      </div>
      <div
        className="grid grid-cols-1 grid-rows-[400px,auto] ml-4 content-stretch justify-center items-center"
        style={{ width: "800px", height: "880px" }}>
        <div className="w-full h-2/3 flex">
          {totalData !== null && (
            <HistorySummary
              win={totalData.winCount}
              lose={totalData.loseCount}
              winrate={totalData.winRate}
            />
          )}
        </div>
        <div className="w-full h-full bg-blue-900">
          {/* 그래프 들어갈 위치 */}
        </div>
      </div>
    </WoodBackground>
  );
};

export default MyPage;
