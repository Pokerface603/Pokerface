import Parchment from "@component/Parchment";
import React from "react";
import SummaryCard from "./SummaryCard";

const HistorySummary = ({ win, lose, winrate }) => {
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
