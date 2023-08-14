const tiers = [
  { goal: 40000000 }, // Queen : 4000만
  { goal: 250000000 }, // King  : 2억 5천만
  { goal: 1000000000 }, // Ace   : 10억
  { goal: 4000000000 }, // Joker : 40억
];

export const getRewardPercent = (reward) => {
  if (reward < tiers[0].goal) {
    return (reward / tiers[0].goal) * 100;
  } else if (reward < tiers[1].goal) {
    return ((reward - tiers[0].goal) / (tiers[1].goal - tiers[0].goal)) * 100;
  } else if (reward < tiers[2].goal) {
    return ((reward - tiers[1].goal) / (tiers[2].goal - tiers[1].goal)) * 100;
  } else if (reward < tiers[3].goal) {
    return ((reward - tiers[2].goal) / (tiers[3].goal - tiers[2].goal)) * 100;
  } else {
    return 100;
  }
};
