/** @param {import('@google-cloud/firestore').QuerySnapshot} snapshot */
function collect(snapshot) {
  const collected = []

  snapshot.forEach((doc) => {
    collected.push(withId(doc))
  })

  return collected
}

/** @param {import('@google-cloud/firestore').DocumentSnapshot} doc */
function withId(doc) {
  return { id: doc.id, ...doc.data() }
}

function withLastUpdated(data) {
  return { ...data, lastUpdated: data.lastUpdated.seconds }
}

module.exports = {
  collect,
  withId,
  withLastUpdated,
}
