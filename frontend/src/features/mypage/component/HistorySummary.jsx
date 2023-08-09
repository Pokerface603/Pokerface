import Parchment from "@component/Parchment";
import React from "react";
import SummaryCard from "./SummaryCard";
import { useEffect } from "react";
import { useState } from "react";
import { getTotalRecord } from "../api/getTotalRecord";
const HistorySummary = () => {
  const [win, setWin] = useState("");
  const [lose, setLose] = useState("");
  const [winrate, setWinrate] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getTotalRecord(3);
        setWin(data);
        setLose(data);
        setWinrate(data);
        // test중, 차후 api에 맞게 수정 예정
        // setWin(data.win);
        // setLose(data.lose);
        // setWinrate(data.winrate);
      } catch (error) {
        console.log("error");
      }
    };
    fetchData();
  }, []);

  return (
    <Parchment
      style={{
        width: "100%",
        height: "100%",
        display: "flex",
        justifyContent: "space-between",
      }}>
      <SummaryCard title="win" count={win} />
      <SummaryCard title="lose" count={lose} />
      <SummaryCard title="winrate" count={winrate} />
    </Parchment>
  );
};

export default HistorySummary;
