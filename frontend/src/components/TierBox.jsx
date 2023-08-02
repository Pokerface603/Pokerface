const TierBox = ({tier, width, height}) => {
    return(
        <img src={tier} alt={tier} width={width} height={height} />
    )
}

export default TierBox;