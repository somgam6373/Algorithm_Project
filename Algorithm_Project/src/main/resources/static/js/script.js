const canvas = document.getElementById("graphCanvas");
const ctx = canvas.getContext("2d");

let nodePositions = {};
let visitOrder = [];
let edgesList = [];
let visitedEdges = []; // 탐색 시 지나간 간선을 저장하는 배열
let directedMode = false;
let currentStep = 0;

/* =========================
   UI 모드 전환 및 초기화
========================= */
function toggleInputMode() {
    const algorithmType = document.getElementById("algorithmType").value;
    const graphControls = document.getElementById("graphControls");
    const arrayControls = document.getElementById("arrayControls");
    const searchInputGroup = document.getElementById("searchInputGroup");

    const isSortAlgo = ["BubbleSort", "QuickSort", "MergeSort", "SelectionSort"].includes(algorithmType);
    const isSearchAlgo = ["BinarySearch"].includes(algorithmType);

    graphControls.style.display = (isSortAlgo || isSearchAlgo) ? "none" : "block";
    arrayControls.style.display = (isSortAlgo || isSearchAlgo) ? "block" : "none";

    if (searchInputGroup) {
        searchInputGroup.style.display = isSortAlgo ? "none" : "inline-block";
    }
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}

/* =========================
   실행 메인 함수
========================= */
function runAlgorithm() {
    const algorithmType = document.getElementById("algorithmType").value;
    const arrayInput = document.getElementById("arrayInput").value;
    const list = arrayInput.trim().split(/\s+/).map(Number);

    if (["BubbleSort", "QuickSort", "MergeSort", "SelectionSort"].includes(algorithmType)) {
        if (isAlreadySorted(list)) {
            alert("⚠️ 배열이 이미 정렬되어 있습니다! 숫자를 섞어서 입력해주세요.");
            return;
        }
        executeSortAlgorithm(algorithmType, list);
    } else if (algorithmType === "BinarySearch") {
        executeArrayAlgorithm(algorithmType, list);
    } else {
        executeGraphAlgorithm(algorithmType);
    }
}

function isAlreadySorted(arr) {
    for (let i = 0; i < arr.length - 1; i++) {
        if (arr[i] > arr[i + 1]) return false;
    }
    return true;
}

/* =========================
   1. 정렬 알고리즘 시각화
========================= */
function executeSortAlgorithm(type, list) {
    fetch(`http://localhost:8080/sort/${type}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ algorithmType: type, list: [...list] })
    })
        .then(res => res.json())
        .then(data => {
            if (data.explore) {
                drawArray(list);
                animateSortSwap(list, data.explore);
            }
        });
}

async function animateSortSwap(workingList, explorePath) {
    for (let i = 0; i < explorePath.length - 1; i++) {
        const idxA = explorePath[i];
        const idxB = explorePath[i + 1];
        if (idxA < idxB && workingList[idxA] > workingList[idxB]) {
            drawArray(workingList, idxA, idxB, true);
            await new Promise(r => setTimeout(r, 800));
            let temp = workingList[idxA];
            workingList[idxA] = workingList[idxB];
            workingList[idxB] = temp;
            drawArray(workingList, idxA, idxB, false);
            await new Promise(r => setTimeout(r, 800));
        } else {
            drawArray(workingList, idxA, idxB, false);
            await new Promise(r => setTimeout(r, 400));
        }
    }
    drawArray(workingList);
}

function drawArray(list, activeA = -1, activeB = -1, isSwapping = false) {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    const boxSize = 60, spacing = 15;
    const totalWidth = list.length * (boxSize + spacing);
    let startX = (canvas.width - totalWidth) / 2;
    const y = canvas.height / 2;

    list.forEach((val, i) => {
        const x = startX + i * (boxSize + spacing);
        ctx.fillStyle = (i === activeA || i === activeB) ? (isSwapping ? "#F44336" : "#FF9800") : "#4CAF50";
        ctx.fillRect(x, y - 30, boxSize, 60);
        ctx.strokeStyle = "#333";
        ctx.strokeRect(x, y - 30, boxSize, 60);
        ctx.fillStyle = "white";
        ctx.font = "bold 18px Arial";
        ctx.textAlign = "center";
        ctx.fillText(val, x + boxSize / 2, y + 8);
    });
}

/* =========================
   2. 그래프 알고리즘 (DFS, BFS) 시각화
========================= */
function executeGraphAlgorithm(type) {
    const nodeCount = parseInt(document.getElementById("nodeCount").value);
    const startNode = parseInt(document.getElementById("startNode").value);
    const edgesInput = document.getElementById("edges").value.trim().split("\n");
    directedMode = document.getElementById("directed").checked;

    fetch(`http://localhost:8080/graph/${type}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nodeCount, startNode, edges: edgesInput, directed: directedMode })
    })
        .then(res => res.json())
        .then(data => {
            document.getElementById("result").textContent = JSON.stringify(data, null, 2);
            visitOrder = data.result;
            edgesList = edgesInput.map(e => e.split(" ").map(Number));
            visitedEdges = []; // 간선 방문 기록 초기화
            currentStep = 0;

            calculateNodePositions(nodeCount);
            animateVisit();
        })
        .catch(err => alert("에러: " + err.message));
}

function calculateNodePositions(nodeCount) {
    nodePositions = {};
    const radius = 200, centerX = canvas.width / 2, centerY = canvas.height / 2;
    for (let i = 1; i <= nodeCount; i++) {
        const angle = (2 * Math.PI / nodeCount) * (i - 1) - (Math.PI / 2);
        nodePositions[i] = { x: centerX + radius * Math.cos(angle), y: centerY + radius * Math.sin(angle) };
    }
}

function animateVisit() {
    if (currentStep >= visitOrder.length) return;

    // 현재 노드에서 다음 노드로 이동할 때 간선 기록
    if (currentStep > 0) {
        const from = visitOrder[currentStep - 1];
        const to = visitOrder[currentStep];

        // 실제로 존재하는 간선인지 확인 (무방향일 경우 순서 상관없이 체크)
        const exists = edgesList.some(e =>
            (e[0] === from && e[1] === to) ||
            (!directedMode && e[0] === to && e[1] === from)
        );

        if (exists) {
            visitedEdges.push({ from, to });
        }
    }

    currentStep++;
    drawGraph();

    if (currentStep < visitOrder.length) {
        setTimeout(animateVisit, 1000);
    }
}

function drawGraph() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // 1. 간선 그리기 (방문한 간선은 굵고 붉은색으로 강조)
    edgesList.forEach(([from, to]) => {
        const p1 = nodePositions[from], p2 = nodePositions[to];
        if (!p1 || !p2) return;

        const isVisited = visitedEdges.some(ve =>
            (ve.from === from && ve.to === to) ||
            (!directedMode && ve.from === to && ve.to === from)
        );

        ctx.beginPath();
        ctx.moveTo(p1.x, p1.y);
        ctx.lineTo(p2.x, p2.y);
        ctx.strokeStyle = isVisited ? "#FF5722" : "#bbb";
        ctx.lineWidth = isVisited ? 5 : 2;
        ctx.stroke();

        if (directedMode) {
            drawArrowHead(p1, p2, isVisited ? "#FF5722" : "#bbb");
        }
    });

    // 2. 노드 그리기
    const visitedSoFar = visitOrder.slice(0, currentStep);
    Object.keys(nodePositions).forEach(id => {
        const { x, y } = nodePositions[id];
        const isVisited = visitedSoFar.includes(parseInt(id));

        ctx.beginPath();
        ctx.arc(x, y, 22, 0, 2 * Math.PI);
        ctx.fillStyle = isVisited ? "#FF5722" : "#4CAF50";
        ctx.fill();
        ctx.strokeStyle = "#333";
        ctx.lineWidth = 2;
        ctx.stroke();

        ctx.fillStyle = "white";
        ctx.font = "bold 14px Arial";
        ctx.textAlign = "center";
        ctx.fillText(id, x, y + 5);
    });
}

function drawArrowHead(p1, p2, color) {
    const headLength = 15;
    const angle = Math.atan2(p2.y - p1.y, p2.x - p1.x);
    const endX = p2.x - 22 * Math.cos(angle);
    const endY = p2.y - 22 * Math.sin(angle);

    ctx.strokeStyle = color;
    ctx.lineWidth = 2;
    ctx.beginPath();
    ctx.moveTo(endX, endY);
    ctx.lineTo(endX - headLength * Math.cos(angle - Math.PI / 6), endY - headLength * Math.sin(angle - Math.PI / 6));
    ctx.moveTo(endX, endY);
    ctx.lineTo(endX - headLength * Math.cos(angle + Math.PI / 6), endY - headLength * Math.sin(angle + Math.PI / 6));
    ctx.stroke();
}

/* =========================
   3. 배열 탐색 알고리즘
========================= */
function executeArrayAlgorithm(type, list) {
    const target = document.getElementById("searchTarget").value;
    fetch(`http://localhost:8080/array/${type}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ algorithmType: type, list, input: parseInt(target) })
    })
        .then(res => res.json())
        .then(data => {
            if (data.explore) animateArraySearch(list, data.explore);
        });
}

function animateArraySearch(list, path) {
    let step = 0;
    const timer = setInterval(() => {
        if (step >= path.length) return clearInterval(timer);
        drawArray(list, path[step]);
        step++;
    }, 1000);
}