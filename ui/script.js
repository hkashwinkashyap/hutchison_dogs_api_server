function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
    
function setColCount() {
    // Set colCount based on the window width
    if (window.innerWidth >= 990) { // Desktop size or larger
        return 4;
    } else if(window.innerWidth>=720 && window.innerWidth<990) {
        return 3;
    }
    else {
        return 2;
    }
}

function loadHomePage() {    
    const dogList = document.getElementById('dog-list');

    dogList.innerHTML = '';


    fetch('http://localhost:8080/dogsApi/getAllDogs')
        .then(response => response.json())
        .then(jsonData => {
            const dogList = document.getElementById('dog-list');
            let row = document.createElement("div");
            row.classList.add("row");
            dogList.appendChild(row);

            let colCount = 0; // Keep track of the number of columns

            for (const breed in jsonData["data"]) {
                if (colCount === setColCount()) {
                    row = document.createElement("div");
                    row.classList.add("row");
                    dogList.appendChild(row);
                    colCount = 0;
                }

                const breedCard = document.createElement("div");
                breedCard.classList.add("card");
                breedCard.classList.add("col");
                row.appendChild(breedCard);

                const breedCardContent = document.createElement("div");
                breedCardContent.classList.add("card-body");
                breedCard.appendChild(breedCardContent);

                const breedTitle = document.createElement("h5");
                breedTitle.classList.add("card-title");
                breedTitle.textContent = capitalizeFirstLetter(breed); // Capitalize the breed name
                breedCardContent.appendChild(breedTitle);

                const breedTypeName = document.createElement("div");
                breedTypeName.classList.add("list-group");

                for (const subBreed of jsonData["data"][breed]) {
                    const breedType = document.createElement("li");
                    breedType.classList.add("list-group-item");
                    breedType.textContent = capitalizeFirstLetter(subBreed); // Capitalize the breedType name
                    breedTypeName.appendChild(breedType);
                }

                breedCardContent.appendChild(breedTypeName);

                colCount++;
            }
        })
        .catch(error => {
            console.error('Error fetching data: ' + error);
        });
}

window.onload = loadHomePage;
window.onresize = loadHomePage;