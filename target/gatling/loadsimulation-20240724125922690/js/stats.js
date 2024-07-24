var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "1000",
        "ok": "0",
        "ko": "1000"
    },
    "minResponseTime": {
        "total": "160",
        "ok": "-",
        "ko": "160"
    },
    "maxResponseTime": {
        "total": "1669",
        "ok": "-",
        "ko": "1669"
    },
    "meanResponseTime": {
        "total": "185",
        "ok": "-",
        "ko": "185"
    },
    "standardDeviation": {
        "total": "108",
        "ok": "-",
        "ko": "108"
    },
    "percentiles1": {
        "total": "170",
        "ok": "-",
        "ko": "170"
    },
    "percentiles2": {
        "total": "174",
        "ok": "-",
        "ko": "174"
    },
    "percentiles3": {
        "total": "196",
        "ok": "-",
        "ko": "196"
    },
    "percentiles4": {
        "total": "670",
        "ok": "-",
        "ko": "670"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 1000,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "20",
        "ok": "-",
        "ko": "20"
    }
},
contents: {
"req_get-authorized--ee895": {
        type: "REQUEST",
        name: "Get Authorized User",
path: "Get Authorized User",
pathFormatted: "req_get-authorized--ee895",
stats: {
    "name": "Get Authorized User",
    "numberOfRequests": {
        "total": "1000",
        "ok": "0",
        "ko": "1000"
    },
    "minResponseTime": {
        "total": "160",
        "ok": "-",
        "ko": "160"
    },
    "maxResponseTime": {
        "total": "1669",
        "ok": "-",
        "ko": "1669"
    },
    "meanResponseTime": {
        "total": "185",
        "ok": "-",
        "ko": "185"
    },
    "standardDeviation": {
        "total": "108",
        "ok": "-",
        "ko": "108"
    },
    "percentiles1": {
        "total": "170",
        "ok": "-",
        "ko": "170"
    },
    "percentiles2": {
        "total": "174",
        "ok": "-",
        "ko": "174"
    },
    "percentiles3": {
        "total": "196",
        "ok": "-",
        "ko": "196"
    },
    "percentiles4": {
        "total": "670",
        "ok": "-",
        "ko": "670"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 1000,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "20",
        "ok": "-",
        "ko": "20"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
