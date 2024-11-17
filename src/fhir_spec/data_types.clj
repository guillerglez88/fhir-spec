(ns fhir-spec.data-types (:require [clojure.spec.alpha :as s]))

;; --- primitives ---

;; Primitive value for string
;; http://hl7.org/fhir/StructureDefinition/string
(s/def :fhir/string string?)

;; Primitive value for markdown
;; http://hl7.org/fhir/StructureDefinition/markdown
(s/def :fhir/markdown (s/and string? (partial re-matches #"^[\s\S]+$")))

;; Primitive value for integer64
;; http://hl7.org/fhir/StructureDefinition/integer64
(s/def :fhir/integer64 integer?)

;; Primitive value for date
;; http://hl7.org/fhir/StructureDefinition/date
(s/def :fhir/date (s/and string? (partial re-matches #"([0-9]([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)(-(0[1-9]|1[0-2])(-(0[1-9]|[1-2][0-9]|3[0-1]))?)?")))

;; Primitive value for url
;; http://hl7.org/fhir/StructureDefinition/url
(s/def :fhir/url (s/and string? (partial re-matches #"\S*")))

;; Primitive value for integer
;; http://hl7.org/fhir/StructureDefinition/integer
(s/def :fhir/integer integer?)

;; Primitive value for base64Binary
;; http://hl7.org/fhir/StructureDefinition/base64Binary
(s/def :fhir/base64Binary (s/and string? (partial re-matches #"(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?")))

;; Primitive value for instant
;; http://hl7.org/fhir/StructureDefinition/instant
(s/def :fhir/instant (s/and string? (partial re-matches #"([0-9]([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)(\.[0-9]{1,9})?(Z|(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))")))

;; Actual xhtml
;; http://hl7.org/fhir/StructureDefinition/xhtml
(s/def :fhir/xhtml string?)

;; Primitive value for uuid
;; http://hl7.org/fhir/StructureDefinition/uuid
(s/def :fhir/uuid (s/and string? (partial re-matches #"urn:uuid:[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")))

;; Primitive value for id
;; http://hl7.org/fhir/StructureDefinition/id
(s/def :fhir/id (s/and string? (partial re-matches #"[A-Za-z0-9\-\.]{1,64}")))

;; Primitive value for unsignedInt
;; http://hl7.org/fhir/StructureDefinition/unsignedInt
(s/def :fhir/unsignedInt pos-int?)

;; Primitive value for canonical
;; http://hl7.org/fhir/StructureDefinition/canonical
(s/def :fhir/canonical (s/and string? (partial re-matches #"\S*")))

;; Primitive value for code
;; http://hl7.org/fhir/StructureDefinition/code
(s/def :fhir/code (s/and string? (partial re-matches #"[^\s]+( [^\s]+)*")))

;; Primitive value for oid
;; http://hl7.org/fhir/StructureDefinition/oid
(s/def :fhir/oid (s/and string? (partial re-matches #"urn:oid:[0-2](\.(0|[1-9][0-9]*))+")))

;; Primitive value for boolean
;; http://hl7.org/fhir/StructureDefinition/boolean
(s/def :fhir/boolean boolean?)

;; Primitive value for time
;; http://hl7.org/fhir/StructureDefinition/time
(s/def :fhir/time (s/and string? (partial re-matches #"([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)(\.[0-9]{1,9})?")))

;; Primitive value for dateTime
;; http://hl7.org/fhir/StructureDefinition/dateTime
(s/def :fhir/dateTime (s/and string? (partial re-matches #"([0-9]([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)(-(0[1-9]|1[0-2])(-(0[1-9]|[1-2][0-9]|3[0-1])(T([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)(\.[0-9]{1,9})?)?)?(Z|(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00)?)?)?")))

;; Primitive value for uri
;; http://hl7.org/fhir/StructureDefinition/uri
(s/def :fhir/uri (s/and string? (partial re-matches #"\S*")))

;; Primitive value for decimal
;; http://hl7.org/fhir/StructureDefinition/decimal
(s/def :fhir/decimal number?)

;; Primitive value for positiveInt
;; http://hl7.org/fhir/StructureDefinition/positiveInt
(s/def :fhir/positiveInt pos-int?)

;; --- complex ---

(s/def :fhir/Base (constantly true))
(s/def :fhir/Element (constantly true))
(s/def :fhir/DataType (constantly true))
(s/def :fhir/BackboneElement (constantly true))
(s/def :fhir/Quantity (constantly true))
(s/def :fhir/SimpleQuantity (constantly true))
(s/def :fhir/Age (constantly true))
(s/def :fhir/Duration (constantly true))
(s/def :fhir/Count (constantly true))
(s/def :fhir/MoneyQuantity (constantly true))
(s/def :fhir/Distance (constantly true))
(s/def :fhir/SampledData (constantly true))
(s/def :fhir/Extension (constantly true))
(s/def :fhir/Ratio (constantly true))
(s/def :fhir/ParameterDefinition (constantly true))
(s/def :fhir/ContactDetail (constantly true))
(s/def :fhir/Address (constantly true))
(s/def :fhir/Coding (constantly true))
(s/def :fhir/Reference (constantly true))
(s/def :fhir/Period (constantly true))
(s/def :fhir/HumanName (constantly true))
(s/def :fhir/RelatedArtifact (constantly true))
(s/def :fhir/Expression (constantly true))
(s/def :fhir/CodeableReference (constantly true))
(s/def :fhir/BackboneType (constantly true))
(s/def :fhir/Signature (constantly true))
(s/def :fhir/Contributor (constantly true))
(s/def :fhir/UsageContext (constantly true))
(s/def :fhir/Meta (constantly true))
(s/def :fhir/Availability (constantly true))
(s/def :fhir/PrimitiveType (constantly true))
(s/def :fhir/ContactPoint (constantly true))
(s/def :fhir/Annotation (constantly true))
(s/def :fhir/Attachment (constantly true))
(s/def :fhir/Narrative (constantly true))
(s/def :fhir/ExtendedContactDetail (constantly true))
(s/def :fhir/RatioRange (constantly true))
(s/def :fhir/TriggerDefinition (constantly true))
(s/def :fhir/Range (constantly true))
(s/def :fhir/CodeableConcept (constantly true))
(s/def :fhir/DataRequirement (constantly true))
(s/def :fhir/Money (constantly true))
(s/def :fhir/Identifier (constantly true))
(s/def :fhir/MonetaryComponent (constantly true))
(s/def :fhir/VirtualServiceDetail (constantly true))
(s/def :fhir/ProductShelfLife (constantly true))
(s/def :fhir/Dosage (constantly true))
(s/def :fhir/MarketingStatus (constantly true))
(s/def :fhir/Timing (constantly true))

;; ---

;; Base for all types and resources
;; http://hl7.org/fhir/StructureDefinition/Base
(s/def :fhir/Base (constantly true))

;; Base for all elements
;; http://hl7.org/fhir/StructureDefinition/Element
(s/def :fhir.Element/id :fhir/string)
(s/def :fhir.Element/extension (s/coll-of :fhir/Extension :kind vector? :distinct true))
(s/def :fhir/Element (s/merge :fhir/Base (s/keys :opt [:fhir.Element/id :fhir.Element/extension])))

;; Reuseable Types
;; http://hl7.org/fhir/StructureDefinition/DataType
(s/def :fhir/DataType :fhir/Element)

;; Base for elements defined inside a resource
;; http://hl7.org/fhir/StructureDefinition/BackboneElement
(s/def :fhir.BackboneElement/modifierExtension (s/coll-of :fhir/Extension :kind vector? :distinct true))
(s/def :fhir/BackboneElement (s/merge :fhir/Element (s/keys :opt [:fhir.BackboneElement/modifierExtension])))

;; A measured or measurable amount
;; http://hl7.org/fhir/StructureDefinition/Quantity
(s/def :fhir.Quantity/value :fhir/decimal)
(s/def :fhir.Quantity/comparator :fhir/code)
(s/def :fhir.Quantity/unit :fhir/string)
(s/def :fhir.Quantity/system :fhir/uri)
(s/def :fhir.Quantity/code :fhir/code)
(s/def :fhir/Quantity (s/merge :fhir/DataType (s/keys :opt [:fhir.Quantity/value :fhir.Quantity/comparator :fhir.Quantity/unit :fhir.Quantity/system :fhir.Quantity/code])))

;; A fixed quantity (no comparator)
;; http://hl7.org/fhir/StructureDefinition/SimpleQuantity
(s/def :fhir/SimpleQuantity :fhir/Quantity)

;; A duration of time during which an organism (or a process) has existed
;; http://hl7.org/fhir/StructureDefinition/Age
(s/def :fhir/Age :fhir/Quantity)

;; A length of time
;; http://hl7.org/fhir/StructureDefinition/Duration
(s/def :fhir/Duration :fhir/Quantity)

;; A measured or measurable amount
;; http://hl7.org/fhir/StructureDefinition/Count
(s/def :fhir/Count :fhir/Quantity)

;; An amount of money. With regard to precision, see [Decimal Precision](datatypes.html#precision)
;; http://hl7.org/fhir/StructureDefinition/MoneyQuantity
(s/def :fhir/MoneyQuantity :fhir/Quantity)

;; A length - a value with a unit that is a physical distance
;; http://hl7.org/fhir/StructureDefinition/Distance
(s/def :fhir/Distance :fhir/Quantity)

;; A series of measurements taken by a device
;; http://hl7.org/fhir/StructureDefinition/SampledData
(s/def :fhir.SampledData/origin :fhir/Quantity)
(s/def :fhir.SampledData/interval :fhir/decimal)
(s/def :fhir.SampledData/intervalUnit :fhir/code)
(s/def :fhir.SampledData/factor :fhir/decimal)
(s/def :fhir.SampledData/lowerLimit :fhir/decimal)
(s/def :fhir.SampledData/upperLimit :fhir/decimal)
(s/def :fhir.SampledData/dimensions :fhir/positiveInt)
(s/def :fhir.SampledData/codeMap :fhir/canonical)
(s/def :fhir.SampledData/offsets :fhir/string)
(s/def :fhir.SampledData/data :fhir/string)
(s/def :fhir/SampledData (s/merge :fhir/DataType (s/keys :req [:fhir.SampledData/origin :fhir.SampledData/intervalUnit :fhir.SampledData/dimensions] :opt [:fhir.SampledData/interval :fhir.SampledData/factor :fhir.SampledData/lowerLimit :fhir.SampledData/upperLimit :fhir.SampledData/codeMap :fhir.SampledData/offsets :fhir.SampledData/data])))

;; Optional Extensions Element
;; http://hl7.org/fhir/StructureDefinition/Extension
(s/def :fhir.Extension/url :fhir/uri)
(s/def :fhir.Extension/valueBase64binary :fhir/base64Binary)
(s/def :fhir.Extension/valueBoolean :fhir/boolean)
(s/def :fhir.Extension/valueCanonical :fhir/canonical)
(s/def :fhir.Extension/valueCode :fhir/code)
(s/def :fhir.Extension/valueDate :fhir/date)
(s/def :fhir.Extension/valueDatetime :fhir/dateTime)
(s/def :fhir.Extension/valueDecimal :fhir/decimal)
(s/def :fhir.Extension/valueId :fhir/id)
(s/def :fhir.Extension/valueInstant :fhir/instant)
(s/def :fhir.Extension/valueInteger :fhir/integer)
(s/def :fhir.Extension/valueInteger64 :fhir/integer64)
(s/def :fhir.Extension/valueMarkdown :fhir/markdown)
(s/def :fhir.Extension/valueOid :fhir/oid)
(s/def :fhir.Extension/valuePositiveint :fhir/positiveInt)
(s/def :fhir.Extension/valueString :fhir/string)
(s/def :fhir.Extension/valueTime :fhir/time)
(s/def :fhir.Extension/valueUnsignedint :fhir/unsignedInt)
(s/def :fhir.Extension/valueUri :fhir/uri)
(s/def :fhir.Extension/valueUrl :fhir/url)
(s/def :fhir.Extension/valueUuid :fhir/uuid)
(s/def :fhir.Extension/valueAddress :fhir/Address)
(s/def :fhir.Extension/valueAge :fhir/Age)
(s/def :fhir.Extension/valueAnnotation :fhir/Annotation)
(s/def :fhir.Extension/valueAttachment :fhir/Attachment)
(s/def :fhir.Extension/valueCodeableconcept :fhir/CodeableConcept)
(s/def :fhir.Extension/valueCodeablereference :fhir/CodeableReference)
(s/def :fhir.Extension/valueCoding :fhir/Coding)
(s/def :fhir.Extension/valueContactpoint :fhir/ContactPoint)
(s/def :fhir.Extension/valueCount :fhir/Count)
(s/def :fhir.Extension/valueDistance :fhir/Distance)
(s/def :fhir.Extension/valueDuration :fhir/Duration)
(s/def :fhir.Extension/valueHumanname :fhir/HumanName)
(s/def :fhir.Extension/valueIdentifier :fhir/Identifier)
(s/def :fhir.Extension/valueMoney :fhir/Money)
(s/def :fhir.Extension/valuePeriod :fhir/Period)
(s/def :fhir.Extension/valueQuantity :fhir/Quantity)
(s/def :fhir.Extension/valueRange :fhir/Range)
(s/def :fhir.Extension/valueRatio :fhir/Ratio)
(s/def :fhir.Extension/valueRatiorange :fhir/RatioRange)
(s/def :fhir.Extension/valueReference :fhir/Reference)
(s/def :fhir.Extension/valueSampleddata :fhir/SampledData)
(s/def :fhir.Extension/valueSignature :fhir/Signature)
(s/def :fhir.Extension/valueTiming :fhir/Timing)
(s/def :fhir.Extension/valueContactdetail :fhir/ContactDetail)
(s/def :fhir.Extension/valueDatarequirement :fhir/DataRequirement)
(s/def :fhir.Extension/valueExpression :fhir/Expression)
(s/def :fhir.Extension/valueParameterdefinition :fhir/ParameterDefinition)
(s/def :fhir.Extension/valueRelatedartifact :fhir/RelatedArtifact)
(s/def :fhir.Extension/valueTriggerdefinition :fhir/TriggerDefinition)
(s/def :fhir.Extension/valueUsagecontext :fhir/UsageContext)
(s/def :fhir.Extension/valueAvailability :fhir/Availability)
(s/def :fhir.Extension/valueExtendedcontactdetail :fhir/ExtendedContactDetail)
(s/def :fhir.Extension/valueDosage :fhir/Dosage)
(s/def :fhir.Extension/valueMeta :fhir/Meta)
(s/def :fhir/Extension (s/merge :fhir/DataType (s/keys :req [:fhir.Extension/url] :opt [:fhir.Extension/valueBase64binary :fhir.Extension/valueBoolean :fhir.Extension/valueCanonical :fhir.Extension/valueCode :fhir.Extension/valueDate :fhir.Extension/valueDatetime :fhir.Extension/valueDecimal :fhir.Extension/valueId :fhir.Extension/valueInstant :fhir.Extension/valueInteger :fhir.Extension/valueInteger64 :fhir.Extension/valueMarkdown :fhir.Extension/valueOid :fhir.Extension/valuePositiveint :fhir.Extension/valueString :fhir.Extension/valueTime :fhir.Extension/valueUnsignedint :fhir.Extension/valueUri :fhir.Extension/valueUrl :fhir.Extension/valueUuid :fhir.Extension/valueAddress :fhir.Extension/valueAge :fhir.Extension/valueAnnotation :fhir.Extension/valueAttachment :fhir.Extension/valueCodeableconcept :fhir.Extension/valueCodeablereference :fhir.Extension/valueCoding :fhir.Extension/valueContactpoint :fhir.Extension/valueCount :fhir.Extension/valueDistance :fhir.Extension/valueDuration :fhir.Extension/valueHumanname :fhir.Extension/valueIdentifier :fhir.Extension/valueMoney :fhir.Extension/valuePeriod :fhir.Extension/valueQuantity :fhir.Extension/valueRange :fhir.Extension/valueRatio :fhir.Extension/valueRatiorange :fhir.Extension/valueReference :fhir.Extension/valueSampleddata :fhir.Extension/valueSignature :fhir.Extension/valueTiming :fhir.Extension/valueContactdetail :fhir.Extension/valueDatarequirement :fhir.Extension/valueExpression :fhir.Extension/valueParameterdefinition :fhir.Extension/valueRelatedartifact :fhir.Extension/valueTriggerdefinition :fhir.Extension/valueUsagecontext :fhir.Extension/valueAvailability :fhir.Extension/valueExtendedcontactdetail :fhir.Extension/valueDosage :fhir.Extension/valueMeta])))

;; A ratio of two Quantity values - a numerator and a denominator
;; http://hl7.org/fhir/StructureDefinition/Ratio
(s/def :fhir.Ratio/numerator :fhir/Quantity)
(s/def :fhir.Ratio/denominator :fhir/Quantity)
(s/def :fhir/Ratio (s/merge :fhir/DataType (s/keys :opt [:fhir.Ratio/numerator :fhir.Ratio/denominator])))

;; Definition of a parameter to a module
;; http://hl7.org/fhir/StructureDefinition/ParameterDefinition
(s/def :fhir.ParameterDefinition/name :fhir/code)
(s/def :fhir.ParameterDefinition/use :fhir/code)
(s/def :fhir.ParameterDefinition/min :fhir/integer)
(s/def :fhir.ParameterDefinition/max :fhir/string)
(s/def :fhir.ParameterDefinition/documentation :fhir/string)
(s/def :fhir.ParameterDefinition/type :fhir/code)
(s/def :fhir.ParameterDefinition/profile :fhir/canonical)
(s/def :fhir/ParameterDefinition (s/merge :fhir/DataType (s/keys :req [:fhir.ParameterDefinition/use :fhir.ParameterDefinition/type] :opt [:fhir.ParameterDefinition/name :fhir.ParameterDefinition/min :fhir.ParameterDefinition/max :fhir.ParameterDefinition/documentation :fhir.ParameterDefinition/profile])))

;; Contact information
;; http://hl7.org/fhir/StructureDefinition/ContactDetail
(s/def :fhir.ContactDetail/name :fhir/string)
(s/def :fhir.ContactDetail/telecom (s/coll-of :fhir/ContactPoint :kind vector? :distinct true))
(s/def :fhir/ContactDetail (s/merge :fhir/DataType (s/keys :opt [:fhir.ContactDetail/name :fhir.ContactDetail/telecom])))

;; An address expressed using postal conventions (as opposed to GPS or other location definition formats)
;; http://hl7.org/fhir/StructureDefinition/Address
(s/def :fhir.Address/use :fhir/code)
(s/def :fhir.Address/type :fhir/code)
(s/def :fhir.Address/text :fhir/string)
(s/def :fhir.Address/line (s/coll-of :fhir/string :kind vector? :distinct true))
(s/def :fhir.Address/city :fhir/string)
(s/def :fhir.Address/district :fhir/string)
(s/def :fhir.Address/state :fhir/string)
(s/def :fhir.Address/postalCode :fhir/string)
(s/def :fhir.Address/country :fhir/string)
(s/def :fhir.Address/period :fhir/Period)
(s/def :fhir/Address (s/merge :fhir/DataType (s/keys :opt [:fhir.Address/use :fhir.Address/type :fhir.Address/text :fhir.Address/line :fhir.Address/city :fhir.Address/district :fhir.Address/state :fhir.Address/postalCode :fhir.Address/country :fhir.Address/period])))

;; A reference to a code defined by a terminology system
;; http://hl7.org/fhir/StructureDefinition/Coding
(s/def :fhir.Coding/system :fhir/uri)
(s/def :fhir.Coding/version :fhir/string)
(s/def :fhir.Coding/code :fhir/code)
(s/def :fhir.Coding/display :fhir/string)
(s/def :fhir.Coding/userSelected :fhir/boolean)
(s/def :fhir/Coding (s/merge :fhir/DataType (s/keys :opt [:fhir.Coding/system :fhir.Coding/version :fhir.Coding/code :fhir.Coding/display :fhir.Coding/userSelected])))

;; A reference from one resource to another
;; http://hl7.org/fhir/StructureDefinition/Reference
(s/def :fhir.Reference/reference :fhir/string)
(s/def :fhir.Reference/type :fhir/uri)
(s/def :fhir.Reference/identifier :fhir/Identifier)
(s/def :fhir.Reference/display :fhir/string)
(s/def :fhir/Reference (s/merge :fhir/DataType (s/keys :opt [:fhir.Reference/reference :fhir.Reference/type :fhir.Reference/identifier :fhir.Reference/display])))

;; Time range defined by start and end date/time
;; http://hl7.org/fhir/StructureDefinition/Period
(s/def :fhir.Period/start :fhir/dateTime)
(s/def :fhir.Period/end :fhir/dateTime)
(s/def :fhir/Period (s/merge :fhir/DataType (s/keys :opt [:fhir.Period/start :fhir.Period/end])))

;; Name of a human or other living entity - parts and usage
;; http://hl7.org/fhir/StructureDefinition/HumanName
(s/def :fhir.HumanName/use :fhir/code)
(s/def :fhir.HumanName/text :fhir/string)
(s/def :fhir.HumanName/family :fhir/string)
(s/def :fhir.HumanName/given (s/coll-of :fhir/string :kind vector? :distinct true))
(s/def :fhir.HumanName/prefix (s/coll-of :fhir/string :kind vector? :distinct true))
(s/def :fhir.HumanName/suffix (s/coll-of :fhir/string :kind vector? :distinct true))
(s/def :fhir.HumanName/period :fhir/Period)
(s/def :fhir/HumanName (s/merge :fhir/DataType (s/keys :opt [:fhir.HumanName/use :fhir.HumanName/text :fhir.HumanName/family :fhir.HumanName/given :fhir.HumanName/prefix :fhir.HumanName/suffix :fhir.HumanName/period])))

;; Related artifacts for a knowledge resource
;; http://hl7.org/fhir/StructureDefinition/RelatedArtifact
(s/def :fhir.RelatedArtifact/type :fhir/code)
(s/def :fhir.RelatedArtifact/classifier (s/coll-of :fhir/CodeableConcept :kind vector? :distinct true))
(s/def :fhir.RelatedArtifact/label :fhir/string)
(s/def :fhir.RelatedArtifact/display :fhir/string)
(s/def :fhir.RelatedArtifact/citation :fhir/markdown)
(s/def :fhir.RelatedArtifact/document :fhir/Attachment)
(s/def :fhir.RelatedArtifact/resource :fhir/canonical)
(s/def :fhir.RelatedArtifact/resourceReference :fhir/Reference)
(s/def :fhir.RelatedArtifact/publicationStatus :fhir/code)
(s/def :fhir.RelatedArtifact/publicationDate :fhir/date)
(s/def :fhir/RelatedArtifact (s/merge :fhir/DataType (s/keys :req [:fhir.RelatedArtifact/type] :opt [:fhir.RelatedArtifact/classifier :fhir.RelatedArtifact/label :fhir.RelatedArtifact/display :fhir.RelatedArtifact/citation :fhir.RelatedArtifact/document :fhir.RelatedArtifact/resource :fhir.RelatedArtifact/resourceReference :fhir.RelatedArtifact/publicationStatus :fhir.RelatedArtifact/publicationDate])))

;; An expression that can be used to generate a value
;; http://hl7.org/fhir/StructureDefinition/Expression
(s/def :fhir.Expression/description :fhir/string)
(s/def :fhir.Expression/name :fhir/code)
(s/def :fhir.Expression/language :fhir/code)
(s/def :fhir.Expression/expression :fhir/string)
(s/def :fhir.Expression/reference :fhir/uri)
(s/def :fhir/Expression (s/merge :fhir/DataType (s/keys :opt [:fhir.Expression/description :fhir.Expression/name :fhir.Expression/language :fhir.Expression/expression :fhir.Expression/reference])))

;; Reference to a resource or a concept
;; http://hl7.org/fhir/StructureDefinition/CodeableReference
(s/def :fhir.CodeableReference/concept :fhir/CodeableConcept)
(s/def :fhir.CodeableReference/reference :fhir/Reference)
(s/def :fhir/CodeableReference (s/merge :fhir/DataType (s/keys :opt [:fhir.CodeableReference/concept :fhir.CodeableReference/reference])))

;; Base for datatypes that can carry modifier extensions
;; http://hl7.org/fhir/StructureDefinition/BackboneType
(s/def :fhir.BackboneType/modifierExtension (s/coll-of :fhir/Extension :kind vector? :distinct true))
(s/def :fhir/BackboneType (s/merge :fhir/DataType (s/keys :opt [:fhir.BackboneType/modifierExtension])))

;; A Signature - XML DigSig, JWS, Graphical image of signature, etc.
;; http://hl7.org/fhir/StructureDefinition/Signature
(s/def :fhir.Signature/type (s/coll-of :fhir/Coding :kind vector? :distinct true))
(s/def :fhir.Signature/when :fhir/instant)
(s/def :fhir.Signature/who :fhir/Reference)
(s/def :fhir.Signature/onBehalfOf :fhir/Reference)
(s/def :fhir.Signature/targetFormat :fhir/code)
(s/def :fhir.Signature/sigFormat :fhir/code)
(s/def :fhir.Signature/data :fhir/base64Binary)
(s/def :fhir/Signature (s/merge :fhir/DataType (s/keys :opt [:fhir.Signature/type :fhir.Signature/when :fhir.Signature/who :fhir.Signature/onBehalfOf :fhir.Signature/targetFormat :fhir.Signature/sigFormat :fhir.Signature/data])))

;; Contributor information
;; http://hl7.org/fhir/StructureDefinition/Contributor
(s/def :fhir.Contributor/type :fhir/code)
(s/def :fhir.Contributor/name :fhir/string)
(s/def :fhir.Contributor/contact (s/coll-of :fhir/ContactDetail :kind vector? :distinct true))
(s/def :fhir/Contributor (s/merge :fhir/DataType (s/keys :req [:fhir.Contributor/type :fhir.Contributor/name] :opt [:fhir.Contributor/contact])))

;; Describes the context of use for a conformance or knowledge resource
;; http://hl7.org/fhir/StructureDefinition/UsageContext
(s/def :fhir.UsageContext/code :fhir/Coding)
(s/def :fhir.UsageContext/valueCodeableconcept :fhir/CodeableConcept)
(s/def :fhir.UsageContext/valueQuantity :fhir/Quantity)
(s/def :fhir.UsageContext/valueRange :fhir/Range)
(s/def :fhir.UsageContext/valueReference :fhir/Reference)
(s/def :fhir/UsageContext (s/merge :fhir/DataType (s/keys :req [:fhir.UsageContext/code :fhir.UsageContext/valueCodeableconcept :fhir.UsageContext/valueQuantity :fhir.UsageContext/valueRange :fhir.UsageContext/valueReference])))

;; Metadata about a resource
;; http://hl7.org/fhir/StructureDefinition/Meta
(s/def :fhir.Meta/versionId :fhir/id)
(s/def :fhir.Meta/lastUpdated :fhir/instant)
(s/def :fhir.Meta/source :fhir/uri)
(s/def :fhir.Meta/profile (s/coll-of :fhir/canonical :kind vector? :distinct true))
(s/def :fhir.Meta/security (s/coll-of :fhir/Coding :kind vector? :distinct true))
(s/def :fhir.Meta/tag (s/coll-of :fhir/Coding :kind vector? :distinct true))
(s/def :fhir/Meta (s/merge :fhir/DataType (s/keys :opt [:fhir.Meta/versionId :fhir.Meta/lastUpdated :fhir.Meta/source :fhir.Meta/profile :fhir.Meta/security :fhir.Meta/tag])))

;; Availability data for an {item}
;; http://hl7.org/fhir/StructureDefinition/Availability
(s/def :fhir.Availability.availableTime/daysOfWeek (s/coll-of :fhir/code :kind vector? :distinct true))
(s/def :fhir.Availability.availableTime/allDay :fhir/boolean)
(s/def :fhir.Availability.availableTime/availableStartTime :fhir/time)
(s/def :fhir.Availability.availableTime/availableEndTime :fhir/time)
(s/def :fhir/Availability.availableTime (s/merge :fhir/DataType (s/keys :opt [:fhir.Availability.availableTime/daysOfWeek :fhir.Availability.availableTime/allDay :fhir.Availability.availableTime/availableStartTime :fhir.Availability.availableTime/availableEndTime])))
(s/def :fhir.Availability.notAvailableTime/description :fhir/string)
(s/def :fhir.Availability.notAvailableTime/during :fhir/Period)
(s/def :fhir/Availability.notAvailableTime (s/merge :fhir/DataType (s/keys :opt [:fhir.Availability.notAvailableTime/description :fhir.Availability.notAvailableTime/during])))
(s/def :fhir.Availability/availableTime (s/coll-of :fhir/Availability.availableTime :kind vector? :distinct true))
(s/def :fhir.Availability/notAvailableTime (s/coll-of :fhir/Availability.notAvailableTime :kind vector? :distinct true))
(s/def :fhir/Availability (s/merge :fhir/DataType (s/keys :opt [:fhir.Availability/availableTime :fhir.Availability/notAvailableTime])))

;; Parent type for DataTypes with a simple value
;; http://hl7.org/fhir/StructureDefinition/PrimitiveType
(s/def :fhir/PrimitiveType :fhir/DataType)

;; Details of a Technology mediated contact point (phone, fax, email, etc.)
;; http://hl7.org/fhir/StructureDefinition/ContactPoint
(s/def :fhir.ContactPoint/system :fhir/code)
(s/def :fhir.ContactPoint/value :fhir/string)
(s/def :fhir.ContactPoint/use :fhir/code)
(s/def :fhir.ContactPoint/rank :fhir/positiveInt)
(s/def :fhir.ContactPoint/period :fhir/Period)
(s/def :fhir/ContactPoint (s/merge :fhir/DataType (s/keys :opt [:fhir.ContactPoint/system :fhir.ContactPoint/value :fhir.ContactPoint/use :fhir.ContactPoint/rank :fhir.ContactPoint/period])))

;; Text node with attribution
;; http://hl7.org/fhir/StructureDefinition/Annotation
(s/def :fhir.Annotation/authorReference :fhir/Reference)
(s/def :fhir.Annotation/authorString :fhir/string)
(s/def :fhir.Annotation/time :fhir/dateTime)
(s/def :fhir.Annotation/text :fhir/markdown)
(s/def :fhir/Annotation (s/merge :fhir/DataType (s/keys :req [:fhir.Annotation/text] :opt [:fhir.Annotation/authorReference :fhir.Annotation/authorString :fhir.Annotation/time])))

;; Content in a format defined elsewhere
;; http://hl7.org/fhir/StructureDefinition/Attachment
(s/def :fhir.Attachment/contentType :fhir/code)
(s/def :fhir.Attachment/language :fhir/code)
(s/def :fhir.Attachment/data :fhir/base64Binary)
(s/def :fhir.Attachment/url :fhir/url)
(s/def :fhir.Attachment/size :fhir/integer64)
(s/def :fhir.Attachment/hash :fhir/base64Binary)
(s/def :fhir.Attachment/title :fhir/string)
(s/def :fhir.Attachment/creation :fhir/dateTime)
(s/def :fhir.Attachment/height :fhir/positiveInt)
(s/def :fhir.Attachment/width :fhir/positiveInt)
(s/def :fhir.Attachment/frames :fhir/positiveInt)
(s/def :fhir.Attachment/duration :fhir/decimal)
(s/def :fhir.Attachment/pages :fhir/positiveInt)
(s/def :fhir/Attachment (s/merge :fhir/DataType (s/keys :opt [:fhir.Attachment/contentType :fhir.Attachment/language :fhir.Attachment/data :fhir.Attachment/url :fhir.Attachment/size :fhir.Attachment/hash :fhir.Attachment/title :fhir.Attachment/creation :fhir.Attachment/height :fhir.Attachment/width :fhir.Attachment/frames :fhir.Attachment/duration :fhir.Attachment/pages])))

;; Human-readable summary of the resource (essential clinical and business information)
;; http://hl7.org/fhir/StructureDefinition/Narrative
(s/def :fhir.Narrative/status :fhir/code)
(s/def :fhir.Narrative/div :fhir/xhtml)
(s/def :fhir/Narrative (s/merge :fhir/DataType (s/keys :req [:fhir.Narrative/status :fhir.Narrative/div])))

;; Contact information
;; http://hl7.org/fhir/StructureDefinition/ExtendedContactDetail
(s/def :fhir.ExtendedContactDetail/purpose :fhir/CodeableConcept)
(s/def :fhir.ExtendedContactDetail/name (s/coll-of :fhir/HumanName :kind vector? :distinct true))
(s/def :fhir.ExtendedContactDetail/telecom (s/coll-of :fhir/ContactPoint :kind vector? :distinct true))
(s/def :fhir.ExtendedContactDetail/address :fhir/Address)
(s/def :fhir.ExtendedContactDetail/organization :fhir/Reference)
(s/def :fhir.ExtendedContactDetail/period :fhir/Period)
(s/def :fhir/ExtendedContactDetail (s/merge :fhir/DataType (s/keys :opt [:fhir.ExtendedContactDetail/purpose :fhir.ExtendedContactDetail/name :fhir.ExtendedContactDetail/telecom :fhir.ExtendedContactDetail/address :fhir.ExtendedContactDetail/organization :fhir.ExtendedContactDetail/period])))

;; Range of ratio values
;; http://hl7.org/fhir/StructureDefinition/RatioRange
(s/def :fhir.RatioRange/lowNumerator :fhir/Quantity)
(s/def :fhir.RatioRange/highNumerator :fhir/Quantity)
(s/def :fhir.RatioRange/denominator :fhir/Quantity)
(s/def :fhir/RatioRange (s/merge :fhir/DataType (s/keys :opt [:fhir.RatioRange/lowNumerator :fhir.RatioRange/highNumerator :fhir.RatioRange/denominator])))

;; Defines an expected trigger for a module
;; http://hl7.org/fhir/StructureDefinition/TriggerDefinition
(s/def :fhir.TriggerDefinition/type :fhir/code)
(s/def :fhir.TriggerDefinition/name :fhir/string)
(s/def :fhir.TriggerDefinition/code :fhir/CodeableConcept)
(s/def :fhir.TriggerDefinition/subscriptionTopic :fhir/canonical)
(s/def :fhir.TriggerDefinition/timingTiming :fhir/Timing)
(s/def :fhir.TriggerDefinition/timingReference :fhir/Reference)
(s/def :fhir.TriggerDefinition/timingDate :fhir/date)
(s/def :fhir.TriggerDefinition/timingDatetime :fhir/dateTime)
(s/def :fhir.TriggerDefinition/data (s/coll-of :fhir/DataRequirement :kind vector? :distinct true))
(s/def :fhir.TriggerDefinition/condition :fhir/Expression)
(s/def :fhir/TriggerDefinition (s/merge :fhir/DataType (s/keys :req [:fhir.TriggerDefinition/type] :opt [:fhir.TriggerDefinition/name :fhir.TriggerDefinition/code :fhir.TriggerDefinition/subscriptionTopic :fhir.TriggerDefinition/timingTiming :fhir.TriggerDefinition/timingReference :fhir.TriggerDefinition/timingDate :fhir.TriggerDefinition/timingDatetime :fhir.TriggerDefinition/data :fhir.TriggerDefinition/condition])))

;; Set of values bounded by low and high
;; http://hl7.org/fhir/StructureDefinition/Range
(s/def :fhir.Range/low :fhir/Quantity)
(s/def :fhir.Range/high :fhir/Quantity)
(s/def :fhir/Range (s/merge :fhir/DataType (s/keys :opt [:fhir.Range/low :fhir.Range/high])))

;; Concept - reference to a terminology or just  text
;; http://hl7.org/fhir/StructureDefinition/CodeableConcept
(s/def :fhir.CodeableConcept/coding (s/coll-of :fhir/Coding :kind vector? :distinct true))
(s/def :fhir.CodeableConcept/text :fhir/string)
(s/def :fhir/CodeableConcept (s/merge :fhir/DataType (s/keys :opt [:fhir.CodeableConcept/coding :fhir.CodeableConcept/text])))

;; Describes a required data item
;; http://hl7.org/fhir/StructureDefinition/DataRequirement
(s/def :fhir.DataRequirement.codeFilter/path :fhir/string)
(s/def :fhir.DataRequirement.codeFilter/searchParam :fhir/string)
(s/def :fhir.DataRequirement.codeFilter/valueSet :fhir/canonical)
(s/def :fhir.DataRequirement.codeFilter/code (s/coll-of :fhir/Coding :kind vector? :distinct true))
(s/def :fhir/DataRequirement.codeFilter (s/merge :fhir/DataType (s/keys :opt [:fhir.DataRequirement.codeFilter/path :fhir.DataRequirement.codeFilter/searchParam :fhir.DataRequirement.codeFilter/valueSet :fhir.DataRequirement.codeFilter/code])))
(s/def :fhir.DataRequirement.dateFilter/path :fhir/string)
(s/def :fhir.DataRequirement.dateFilter/searchParam :fhir/string)
(s/def :fhir.DataRequirement.dateFilter/valueDatetime :fhir/dateTime)
(s/def :fhir.DataRequirement.dateFilter/valuePeriod :fhir/Period)
(s/def :fhir.DataRequirement.dateFilter/valueDuration :fhir/Duration)
(s/def :fhir/DataRequirement.dateFilter (s/merge :fhir/DataType (s/keys :opt [:fhir.DataRequirement.dateFilter/path :fhir.DataRequirement.dateFilter/searchParam :fhir.DataRequirement.dateFilter/valueDatetime :fhir.DataRequirement.dateFilter/valuePeriod :fhir.DataRequirement.dateFilter/valueDuration])))
(s/def :fhir.DataRequirement.valueFilter/path :fhir/string)
(s/def :fhir.DataRequirement.valueFilter/searchParam :fhir/string)
(s/def :fhir.DataRequirement.valueFilter/comparator :fhir/code)
(s/def :fhir.DataRequirement.valueFilter/valueDatetime :fhir/dateTime)
(s/def :fhir.DataRequirement.valueFilter/valuePeriod :fhir/Period)
(s/def :fhir.DataRequirement.valueFilter/valueDuration :fhir/Duration)
(s/def :fhir/DataRequirement.valueFilter (s/merge :fhir/DataType (s/keys :opt [:fhir.DataRequirement.valueFilter/path :fhir.DataRequirement.valueFilter/searchParam :fhir.DataRequirement.valueFilter/comparator :fhir.DataRequirement.valueFilter/valueDatetime :fhir.DataRequirement.valueFilter/valuePeriod :fhir.DataRequirement.valueFilter/valueDuration])))
(s/def :fhir.DataRequirement.sort/path :fhir/string)
(s/def :fhir.DataRequirement.sort/direction :fhir/code)
(s/def :fhir/DataRequirement.sort (s/merge :fhir/DataType (s/keys :req [:fhir.DataRequirement.sort/path :fhir.DataRequirement.sort/direction])))
(s/def :fhir.DataRequirement/type :fhir/code)
(s/def :fhir.DataRequirement/profile (s/coll-of :fhir/canonical :kind vector? :distinct true))
(s/def :fhir.DataRequirement/subjectCodeableconcept :fhir/CodeableConcept)
(s/def :fhir.DataRequirement/subjectReference :fhir/Reference)
(s/def :fhir.DataRequirement/mustSupport (s/coll-of :fhir/string :kind vector? :distinct true))
(s/def :fhir.DataRequirement/codeFilter (s/coll-of :fhir/DataRequirement.codeFilter :kind vector? :distinct true))
(s/def :fhir.DataRequirement/dateFilter (s/coll-of :fhir/DataRequirement.dateFilter :kind vector? :distinct true))
(s/def :fhir.DataRequirement/valueFilter (s/coll-of :fhir/DataRequirement.valueFilter :kind vector? :distinct true))
(s/def :fhir.DataRequirement/limit :fhir/positiveInt)
(s/def :fhir.DataRequirement/sort (s/coll-of :fhir/DataRequirement.sort :kind vector? :distinct true))
(s/def :fhir/DataRequirement (s/merge :fhir/DataType (s/keys :req [:fhir.DataRequirement/type] :opt [:fhir.DataRequirement/profile :fhir.DataRequirement/subjectCodeableconcept :fhir.DataRequirement/subjectReference :fhir.DataRequirement/mustSupport :fhir.DataRequirement/codeFilter :fhir.DataRequirement/dateFilter :fhir.DataRequirement/valueFilter :fhir.DataRequirement/limit :fhir.DataRequirement/sort])))

;; An amount of economic utility in some recognized currency
;; http://hl7.org/fhir/StructureDefinition/Money
(s/def :fhir.Money/value :fhir/decimal)
(s/def :fhir.Money/currency :fhir/code)
(s/def :fhir/Money (s/merge :fhir/DataType (s/keys :opt [:fhir.Money/value :fhir.Money/currency])))

;; An identifier intended for computation
;; http://hl7.org/fhir/StructureDefinition/Identifier
(s/def :fhir.Identifier/use :fhir/code)
(s/def :fhir.Identifier/type :fhir/CodeableConcept)
(s/def :fhir.Identifier/system :fhir/uri)
(s/def :fhir.Identifier/value :fhir/string)
(s/def :fhir.Identifier/period :fhir/Period)
(s/def :fhir.Identifier/assigner :fhir/Reference)
(s/def :fhir/Identifier (s/merge :fhir/DataType (s/keys :opt [:fhir.Identifier/use :fhir.Identifier/type :fhir.Identifier/system :fhir.Identifier/value :fhir.Identifier/period :fhir.Identifier/assigner])))

;; Availability data for an {item}
;; http://hl7.org/fhir/StructureDefinition/MonetaryComponent
(s/def :fhir.MonetaryComponent/type :fhir/code)
(s/def :fhir.MonetaryComponent/code :fhir/CodeableConcept)
(s/def :fhir.MonetaryComponent/factor :fhir/decimal)
(s/def :fhir.MonetaryComponent/amount :fhir/Money)
(s/def :fhir/MonetaryComponent (s/merge :fhir/DataType (s/keys :req [:fhir.MonetaryComponent/type] :opt [:fhir.MonetaryComponent/code :fhir.MonetaryComponent/factor :fhir.MonetaryComponent/amount])))

;; Virtual Service Contact Details
;; http://hl7.org/fhir/StructureDefinition/VirtualServiceDetail
(s/def :fhir.VirtualServiceDetail/channelType :fhir/Coding)
(s/def :fhir.VirtualServiceDetail/addressUrl :fhir/url)
(s/def :fhir.VirtualServiceDetail/addressString :fhir/string)
(s/def :fhir.VirtualServiceDetail/addressContactpoint :fhir/ContactPoint)
(s/def :fhir.VirtualServiceDetail/addressExtendedcontactdetail :fhir/ExtendedContactDetail)
(s/def :fhir.VirtualServiceDetail/additionalInfo (s/coll-of :fhir/url :kind vector? :distinct true))
(s/def :fhir.VirtualServiceDetail/maxParticipants :fhir/positiveInt)
(s/def :fhir.VirtualServiceDetail/sessionKey :fhir/string)
(s/def :fhir/VirtualServiceDetail (s/merge :fhir/DataType (s/keys :opt [:fhir.VirtualServiceDetail/channelType :fhir.VirtualServiceDetail/addressUrl :fhir.VirtualServiceDetail/addressString :fhir.VirtualServiceDetail/addressContactpoint :fhir.VirtualServiceDetail/addressExtendedcontactdetail :fhir.VirtualServiceDetail/additionalInfo :fhir.VirtualServiceDetail/maxParticipants :fhir.VirtualServiceDetail/sessionKey])))

;; The shelf-life and storage information for a medicinal product item or container can be described using this class
;; http://hl7.org/fhir/StructureDefinition/ProductShelfLife
(s/def :fhir.ProductShelfLife/type :fhir/CodeableConcept)
(s/def :fhir.ProductShelfLife/periodDuration :fhir/Duration)
(s/def :fhir.ProductShelfLife/periodString :fhir/string)
(s/def :fhir.ProductShelfLife/specialPrecautionsForStorage (s/coll-of :fhir/CodeableConcept :kind vector? :distinct true))
(s/def :fhir/ProductShelfLife (s/merge :fhir/BackboneType (s/keys :opt [:fhir.ProductShelfLife/type :fhir.ProductShelfLife/periodDuration :fhir.ProductShelfLife/periodString :fhir.ProductShelfLife/specialPrecautionsForStorage])))

;; How the medication is/was taken or should be taken
;; http://hl7.org/fhir/StructureDefinition/Dosage
(s/def :fhir.Dosage.doseAndRate/type :fhir/CodeableConcept)
(s/def :fhir.Dosage.doseAndRate/doseRange :fhir/Range)
(s/def :fhir.Dosage.doseAndRate/doseQuantity :fhir/Quantity)
(s/def :fhir.Dosage.doseAndRate/rateRatio :fhir/Ratio)
(s/def :fhir.Dosage.doseAndRate/rateRange :fhir/Range)
(s/def :fhir.Dosage.doseAndRate/rateQuantity :fhir/Quantity)
(s/def :fhir/Dosage.doseAndRate (s/merge :fhir/BackboneType (s/keys :opt [:fhir.Dosage.doseAndRate/type :fhir.Dosage.doseAndRate/doseRange :fhir.Dosage.doseAndRate/doseQuantity :fhir.Dosage.doseAndRate/rateRatio :fhir.Dosage.doseAndRate/rateRange :fhir.Dosage.doseAndRate/rateQuantity])))
(s/def :fhir.Dosage/sequence :fhir/integer)
(s/def :fhir.Dosage/text :fhir/string)
(s/def :fhir.Dosage/additionalInstruction (s/coll-of :fhir/CodeableConcept :kind vector? :distinct true))
(s/def :fhir.Dosage/patientInstruction :fhir/string)
(s/def :fhir.Dosage/timing :fhir/Timing)
(s/def :fhir.Dosage/asNeeded :fhir/boolean)
(s/def :fhir.Dosage/asNeededFor (s/coll-of :fhir/CodeableConcept :kind vector? :distinct true))
(s/def :fhir.Dosage/site :fhir/CodeableConcept)
(s/def :fhir.Dosage/route :fhir/CodeableConcept)
(s/def :fhir.Dosage/method :fhir/CodeableConcept)
(s/def :fhir.Dosage/doseAndRate (s/coll-of :fhir/Dosage.doseAndRate :kind vector? :distinct true))
(s/def :fhir.Dosage/maxDosePerPeriod (s/coll-of :fhir/Ratio :kind vector? :distinct true))
(s/def :fhir.Dosage/maxDosePerAdministration :fhir/Quantity)
(s/def :fhir.Dosage/maxDosePerLifetime :fhir/Quantity)
(s/def :fhir/Dosage (s/merge :fhir/BackboneType (s/keys :opt [:fhir.Dosage/sequence :fhir.Dosage/text :fhir.Dosage/additionalInstruction :fhir.Dosage/patientInstruction :fhir.Dosage/timing :fhir.Dosage/asNeeded :fhir.Dosage/asNeededFor :fhir.Dosage/site :fhir.Dosage/route :fhir.Dosage/method :fhir.Dosage/doseAndRate :fhir.Dosage/maxDosePerPeriod :fhir.Dosage/maxDosePerAdministration :fhir.Dosage/maxDosePerLifetime])))

;; The marketing status describes the date when a medicinal product is actually put on the market or the date as of which it is no longer available
;; http://hl7.org/fhir/StructureDefinition/MarketingStatus
(s/def :fhir.MarketingStatus/country :fhir/CodeableConcept)
(s/def :fhir.MarketingStatus/jurisdiction :fhir/CodeableConcept)
(s/def :fhir.MarketingStatus/status :fhir/CodeableConcept)
(s/def :fhir.MarketingStatus/dateRange :fhir/Period)
(s/def :fhir.MarketingStatus/restoreDate :fhir/dateTime)
(s/def :fhir/MarketingStatus (s/merge :fhir/BackboneType (s/keys :req [:fhir.MarketingStatus/status] :opt [:fhir.MarketingStatus/country :fhir.MarketingStatus/jurisdiction :fhir.MarketingStatus/dateRange :fhir.MarketingStatus/restoreDate])))

;; A timing schedule that specifies an event that may occur multiple times
;; http://hl7.org/fhir/StructureDefinition/Timing
(s/def :fhir.Timing.repeat/boundsDuration :fhir/Duration)
(s/def :fhir.Timing.repeat/boundsRange :fhir/Range)
(s/def :fhir.Timing.repeat/boundsPeriod :fhir/Period)
(s/def :fhir.Timing.repeat/count :fhir/positiveInt)
(s/def :fhir.Timing.repeat/countMax :fhir/positiveInt)
(s/def :fhir.Timing.repeat/duration :fhir/decimal)
(s/def :fhir.Timing.repeat/durationMax :fhir/decimal)
(s/def :fhir.Timing.repeat/durationUnit :fhir/code)
(s/def :fhir.Timing.repeat/frequency :fhir/positiveInt)
(s/def :fhir.Timing.repeat/frequencyMax :fhir/positiveInt)
(s/def :fhir.Timing.repeat/period :fhir/decimal)
(s/def :fhir.Timing.repeat/periodMax :fhir/decimal)
(s/def :fhir.Timing.repeat/periodUnit :fhir/code)
(s/def :fhir.Timing.repeat/dayOfWeek (s/coll-of :fhir/code :kind vector? :distinct true))
(s/def :fhir.Timing.repeat/timeOfDay (s/coll-of :fhir/time :kind vector? :distinct true))
(s/def :fhir.Timing.repeat/when (s/coll-of :fhir/code :kind vector? :distinct true))
(s/def :fhir.Timing.repeat/offset :fhir/unsignedInt)
(s/def :fhir/Timing.repeat (s/merge :fhir/BackboneType (s/keys :opt [:fhir.Timing.repeat/boundsDuration :fhir.Timing.repeat/boundsRange :fhir.Timing.repeat/boundsPeriod :fhir.Timing.repeat/count :fhir.Timing.repeat/countMax :fhir.Timing.repeat/duration :fhir.Timing.repeat/durationMax :fhir.Timing.repeat/durationUnit :fhir.Timing.repeat/frequency :fhir.Timing.repeat/frequencyMax :fhir.Timing.repeat/period :fhir.Timing.repeat/periodMax :fhir.Timing.repeat/periodUnit :fhir.Timing.repeat/dayOfWeek :fhir.Timing.repeat/timeOfDay :fhir.Timing.repeat/when :fhir.Timing.repeat/offset])))
(s/def :fhir.Timing/event (s/coll-of :fhir/dateTime :kind vector? :distinct true))
(s/def :fhir.Timing/repeat :fhir/Timing.repeat)
(s/def :fhir.Timing/code :fhir/CodeableConcept)
(s/def :fhir/Timing (s/merge :fhir/BackboneType (s/keys :opt [:fhir.Timing/event :fhir.Timing/repeat :fhir.Timing/code])))

