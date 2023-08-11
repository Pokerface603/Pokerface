import React from "react";
import TabItem from "./TabItem";

const Tab = ({ onClickTab, mode }) => {
  return (
    <>
      {/* Tab은 수정이 필요함. 뭐가 연결된건지 전혀 모르겠음. */}
      <ul className="flex list-none flex-row flex-wrap pl-0">
        <TabItem
          mode={"NORMAL"}
          onClick={onClickTab}
          isSelected={mode === "NORMAL"}
        />
        <TabItem
          mode={"BLIND"}
          onClick={onClickTab}
          isSelected={mode === "BLIND"}
        />
        <TabItem
          mode={"EMOTION"}
          onClick={onClickTab}
          isSelected={mode === "EMOTION"}
        />
      </ul>
    </>
  );
};

export default Tab;
